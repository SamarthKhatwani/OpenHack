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
        let user = JSON.parse(localStorage.getItem(AppConstants.USER_FIREBASE_DETAILS));
        this.getCall(URI.GET_PROFILE + '/?email=' + user.email, success, failure, true);
    }

    /**
    * Get Hackathon Call - Get Hackathon api call
    * Takes success and failure operations
    *
    * Required params: null
    */
    getHackathon(role,success, failure) {
        let user = JSON.parse(localStorage.getItem(AppConstants.USER_FIREBASE_DETAILS));
        this.getCall(URI.LIST_HACKATHON + '/?email=' + user.email +'&role='+ role, success, failure, true);
    }

    /**
    * Get Organization List Call - Get Profile api call
    * Takes success and failure operations
    *
    * Required params: null
    */
    getOrganizationList(success, failure) {
        let user = JSON.parse(localStorage.getItem(AppConstants.USER_FIREBASE_DETAILS));
        this.getCall(URI.GET_LIST_ORGANIZATION + '/?email=' + user.email, success, failure, true);
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
   * Search Organizations Call - Search Organizations api call
   * Takes success and failure operations
   *
   * Required params: null
   */
    fetchEventPrice(email, eventName, success, failure) {
        this.getCall(URI.FETCH_PRICE + '/?email=' + email + "&eventName=" + eventName, success, failure, true);
    }

    /**
    * Update User Profile Call - Update User Profile api call
    * Takes success and failure operations
    *
    * Required params: null
    */
    updateProfile(details, success, failure) {
        this.postCall(URI.UPDATE_PROFILE, details, success, failure, true);
    }

    /**
    * Create Organization Call - Create Organizationapi call
    * Takes success and failure operations
    *
    * Required params: Name, Address, Description
    */
    createOrganization(details, success, failure) {
        this.postCall(URI.CREATE_ORGANIZATION, details, success, failure, true);
    }

    /**
   * Respond Organization Request Call - Respond Organization Request api call
   * Takes success and failure operations
   *
   * Required params: email, isApproved
   */
    respondRequest(details, success, failure) {
        this.postCall(URI.RESPOND_REQUEST, details, success, failure, true);
    }

    /**
   * Create Update Hackathon Request Call - Create Update Hackathon Request api call
   * Takes success and failure operations
   *
   * Required params:
   */
    createUpdateHackathon(details, success, failure) {
        this.postCall(URI.CREATE_UPDATE_HACKATHON, details, success, failure, true);
    }



}