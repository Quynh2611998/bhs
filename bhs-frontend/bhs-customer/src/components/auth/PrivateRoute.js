import React from "react"
import { Route, Redirect } from "react-router-dom"
import { toast } from "react-toastify";
import { getAuthToken } from "../../common/common-function";

function PrivateRoute({ component: Component, ...rest }) {

    const isAuthenticate = getAuthToken() ? true : false
    if (!isAuthenticate) {
        toast.error("You need to sign in first");
    }

    return (
        <Route
            {...rest}
            render={(props) => isAuthenticate === true
                ? <Component {...props} />
                : <Redirect to={{ pathname: '/login', state: { from: props.location } }} />}
        />
    )
}

export default PrivateRoute;