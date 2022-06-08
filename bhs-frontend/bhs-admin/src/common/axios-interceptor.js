import axios from 'axios';
import { getAuthToken, getNonAuthenticationApi } from './common-function';

export function initRequestInterceptor(routingHistory, toast) {
    axios.interceptors.request.use(req => {
        const token = getAuthToken();
        if (token !== null) {
            req.headers.Authorization = 'Bearer ' + token;
        }

        const lstNonAuthApi = getNonAuthenticationApi()
        let isAuthen = true;
        for (let i = 0; i < lstNonAuthApi.length; i++) {
            if (req.url.includes(lstNonAuthApi[i])) {
                isAuthen = false;
                break;
            }
        };
        if (isAuthen && token === null) {
            routingHistory.replace('/login');
            toast.error("You need to sign in first");
        } else {
            return req;
        }
    });
}

export function initResponseInterceptor(routingHistory, toast) {
    axios.interceptors.response.use(
        (res) => {
            if ((res.status === 200 || res.status === 201 || res.status === 204 || res.status === 302) && res.config.method.toLocaleLowerCase() !== 'options') {
                const alertMessage = res.headers['x-bhs-alert'];
                if (alertMessage !== null) {
                    toast.success(alertMessage);
                }
            }
            return res;
        },
        (err) => {
            try {
                let errorMessage = null;
                if (err.response.headers !== null && err.response.headers['x-bhs-error'] !== null) {
                    errorMessage = err.response.headers['x-bhs-error'];
                }
                if (errorMessage !== null) {
                    toast.error(errorMessage);
                }
            } catch (error1) {
                try {
                    if (err.response.status === 403) {
                        routingHistory.replace('/login');
                        toast.error("You need to sign in first");
                    } else {
                        toast.error('Something went wrong...');
                    }
                } catch (error2) {
                    toast.error('Something went wrong...');
                }
            }
            return err;
        }
    );
}