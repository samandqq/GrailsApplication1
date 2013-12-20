package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException

class Domain1Controller {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [domain1InstanceList: Domain1.list(params), domain1InstanceTotal: Domain1.count()]
    }

    def create() {
        [domain1Instance: new Domain1(params)]
    }

    def save() {
        def domain1Instance = new Domain1(params)
        if (!domain1Instance.save(flush: true)) {
            render(view: "create", model: [domain1Instance: domain1Instance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'domain1.label', default: 'Domain1'), domain1Instance.id])
        redirect(action: "show", id: domain1Instance.id)
    }

    def show(Long id) {
        def domain1Instance = Domain1.get(id)
        if (!domain1Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domain1.label', default: 'Domain1'), id])
            redirect(action: "list")
            return
        }

        [domain1Instance: domain1Instance]
    }

    def edit(Long id) {
        def domain1Instance = Domain1.get(id)
        if (!domain1Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domain1.label', default: 'Domain1'), id])
            redirect(action: "list")
            return
        }

        [domain1Instance: domain1Instance]
    }

    def update(Long id, Long version) {
        def domain1Instance = Domain1.get(id)
        if (!domain1Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domain1.label', default: 'Domain1'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (domain1Instance.version > version) {
                domain1Instance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'domain1.label', default: 'Domain1')] as Object[],
                          "Another user has updated this Domain1 while you were editing")
                render(view: "edit", model: [domain1Instance: domain1Instance])
                return
            }
        }

        domain1Instance.properties = params

        if (!domain1Instance.save(flush: true)) {
            render(view: "edit", model: [domain1Instance: domain1Instance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'domain1.label', default: 'Domain1'), domain1Instance.id])
        redirect(action: "show", id: domain1Instance.id)
    }

    def delete(Long id) {
        def domain1Instance = Domain1.get(id)
        if (!domain1Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'domain1.label', default: 'Domain1'), id])
            redirect(action: "list")
            return
        }

        try {
            domain1Instance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'domain1.label', default: 'Domain1'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'domain1.label', default: 'Domain1'), id])
            redirect(action: "show", id: id)
        }
    }
}
