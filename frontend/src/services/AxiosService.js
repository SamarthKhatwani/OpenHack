import axios from 'axios';
import URI from "../constants/uri";
import AppConstants from "../constants/AppConstants";
class AxiosService {
    constructor() { }

    api = axios.create({
        baseURL: URI.BASE_URL,
        timeout: 10000,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
    });

    async postCall(path, details, success, failure, isAuthorized = false) {
        let config;
        if (isAuthorized) {
            let token = JSON.parse(localStorage.getItem(AppConstants.AUTH_TOKEN));
            config = { headers: { 'Authorization': (token ? token : {}) }, withCredentials: true };
        }
        else {
            config = { withCredentials: true };
        }
        console.log(config);
        try {
            const response = await this.api.post(path, details, config);
            success(this.handleResponse(response));
        }
        catch (err) {
            console.error(err);
            failure(this.handleError(err));
        }
    }

    async getCall(path, success, failure, isAuthorized = false) {
        let config;
        if (isAuthorized) {
            let token = JSON.parse(localStorage.getItem(AppConstants.AUTH_TOKEN));
            config = { headers: { 'Authorization': (token ? token : {}) }, withCredentials: true };
        }
        else {
            config = { withCredentials: true, crossDomain: true };
        }
        try {
            const response = await this.api.get(path, config);
            success(this.handleResponse(response));
        }
        catch (err) {
            console.error(err);
            failure(this.handleError(err));
        }
    }

    async deleteCall(path, details = null, success, failure, isAuthorized = false) {
        let config;
        if (isAuthorized) {
            let token = JSON.parse(localStorage.getItem(AppConstants.AUTH_TOKEN));
            config = { headers: { 'Authorization': (token ? token : {}) }, withCredentials: true };
        }
        else {
            config = { withCredentials: true };
        }
        console.log(config);
        try {
            const response = await this.api.delete(path, details, config);
            success(this.handleResponse(response));
        }
        catch (err) {
            console.error(err);
            failure(this.handleError(err));
        }
    }

    handleResponse(response) {
        if (response.data) {
            return response.data;
        }
        return response;
    }


    handleError(error) {
        if (error.response && error.response.status === 401) {
            // logoutReset();
        }
        if (error.response) {
            const errorMessage = (error.response.data && error.response.data.message) || error.message;
            return errorMessage;
        }
        return error.message;
    }
}
export default AxiosService;






