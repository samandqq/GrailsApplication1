package grailsapplication1



import org.junit.*
import grails.test.mixin.*

@TestFor(Domain1Controller)
@Mock(Domain1)
class Domain1ControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/domain1/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.domain1InstanceList.size() == 0
        assert model.domain1InstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.domain1Instance != null
    }

    void testSave() {
        controller.save()

        assert model.domain1Instance != null
        assert view == '/domain1/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/domain1/show/1'
        assert controller.flash.message != null
        assert Domain1.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/domain1/list'

        populateValidParams(params)
        def domain1 = new Domain1(params)

        assert domain1.save() != null

        params.id = domain1.id

        def model = controller.show()

        assert model.domain1Instance == domain1
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/domain1/list'

        populateValidParams(params)
        def domain1 = new Domain1(params)

        assert domain1.save() != null

        params.id = domain1.id

        def model = controller.edit()

        assert model.domain1Instance == domain1
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/domain1/list'

        response.reset()

        populateValidParams(params)
        def domain1 = new Domain1(params)

        assert domain1.save() != null

        // test invalid parameters in update
        params.id = domain1.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/domain1/edit"
        assert model.domain1Instance != null

        domain1.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/domain1/show/$domain1.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        domain1.clearErrors()

        populateValidParams(params)
        params.id = domain1.id
        params.version = -1
        controller.update()

        assert view == "/domain1/edit"
        assert model.domain1Instance != null
        assert model.domain1Instance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/domain1/list'

        response.reset()

        populateValidParams(params)
        def domain1 = new Domain1(params)

        assert domain1.save() != null
        assert Domain1.count() == 1

        params.id = domain1.id

        controller.delete()

        assert Domain1.count() == 0
        assert Domain1.get(domain1.id) == null
        assert response.redirectedUrl == '/domain1/list'
    }
}
