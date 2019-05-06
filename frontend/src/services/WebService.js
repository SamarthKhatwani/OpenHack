import AxiosService from "./AxiosService";
import URI from "../constants/uri"
import AppConstants from "../constants/AppConstants";

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
    * Required params: screenName
    */
    screenNameLookup(screenName, success, failure) {
        this.getCall(URI.LOOKUP_SCREEN_NAME + '/?screenName=' + screenName, success, failure, false);
    }

    /**
    * SignUp Call - User SignUp api call
    * Takes success and failure operations
    *
    * Required params: Name, email, screenName in detail object.
    */
    signUp(details, success, failure) {
        this.postCall(URI.SIGNUP, details, success, failure, false);
    }

    /**
    * Get Profile Call - Get Profile api call
    * Takes success and failure operations
    *
    * Required params: null
    */
    getProfile(success, failure) {
        let user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
        this.getCall(URI.GET_PROFILE + '/?email=' + user.email, success, failure, true);
    }

    /**
    * Search Organizations Call - Search Organizations api call
    * Takes success and failure operations
    *
    * Required params: null
    */
    searchOrganization(success, failure) {
        this.getCall(URI.SEARCH_ORGANIZATION + '/?name=' + "", success, failure, true);
    }

    /**
    * Update User Profile Call - Update User Profile api call
    * Takes success and failure operations
    *
    * Required params: null
    */
   updateProfile(details,success, failure) {
    this.postCall(URI.UPDATE_PROFILE, details, success, failure, true);
}

}