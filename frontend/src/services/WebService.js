import AxiosService from "./AxiosService";
import URI from "../constants/uri"

export default class WebService extends AxiosService {

    static instance = null;

    constructor() {
        super();
    }

    /** Singleton Patter
    * @returns {WebService}
    */
    static getInstance() {
        if (WebService.instance == null) {
            WebService.instance = new WebService();
        }
        return this.instance;
    }

    /**
    * Screen Name Lookup Call - User login api call
    * Takes success and failure operations
    *
    * Required params: username, password and role in detail object.
    */
    screenNameLookup(screenName, success, failure) {
        this.getCall(URI.LOOKUP_SCREEN_NAME + '/?screenName=' + screenName, success, failure, false);
    }

    /**
    * SignUp Call - User SignUp api call
    * Takes success and failure operations
    *
    * Required params: firstName, lastName, email, password, role in detail object.
    */
    signUp(details, success, failure) {
        this.postCall(URI.SIGNUP, details, success, failure, false);
    }

}