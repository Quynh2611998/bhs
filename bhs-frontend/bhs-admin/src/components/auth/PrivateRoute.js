import React from "react"
import { Route, Redirect } from "react-router-dom"
import { toast } from "react-toastify";
import { getAuthToken, getRole } from "../../common/common-function";

function PrivateRoute({ component: Component, ...rest }) {

    const isAuthenticate = getAuthToken() ? true : false;
    const roleName = getRole();
    if (!isAuthenticate) {
        toast.error("You need to sign in first");
    } else {
        const roleName = getRole();
        if (roleName) {
            if (roleName !== 'Admin') {
                toast.error("You don't have authority to use this resource!");
            }
        }
    }

    return (
        <Route
            {...rest}
            render={(props) => (isAuthenticate === true && roleName === 'Admin')
                ? <Component {...props} />
                : <Redirect to={{ pathname: '/login', state: { from: props.location } }} />}
        />
    )
}

export default PrivateRoute;