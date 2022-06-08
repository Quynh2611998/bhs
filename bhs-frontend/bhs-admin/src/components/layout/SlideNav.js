import { Link, useHistory } from 'react-router-dom'

function SlideNav() {
    return (
        <div id="layoutSidenav_nav">
            <nav className="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div className="sb-sidenav-menu">
                    <div className="nav">
                        <div className="sb-sidenav-menu-heading">Core</div>
                        <Link to='/' className="nav-link">
                            <div className="sb-nav-link-icon"><i className="fas fa-tachometer-alt"></i></div>
                            Dashboard
                        </Link>
                        <div className="sb-sidenav-menu-heading">Interface</div>
                        <a className="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                            <div className="sb-nav-link-icon"><i className="fa fa-scissors" aria-hidden="true"></i></div>
                            Services
                            <div className="sb-sidenav-collapse-arrow"><i className="fa fa-angle-down" aria-hidden="true"></i></div>
                        </a>
                        <div className="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                            <nav className="sb-sidenav-menu-nested nav">
                                <Link className="nav-link" to='/category'>
                                    Category
                                </Link>
                                <Link className="nav-link" to='/service'>
                                    Service
                                </Link>
                                <Link className="nav-link" to='/option'>
                                    Option
                                </Link>
                            </nav>
                        </div>
                        <a className="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseVoucher" aria-expanded="false" aria-controls="collapsePages">
                            <div className="sb-nav-link-icon"><i className="fa fa-ticket" aria-hidden="true"></i></div>
                            Voucher
                            <div className="sb-sidenav-collapse-arrow"><i className="fa fa-angle-down" aria-hidden="true"></i></div>
                        </a>
                        <div className="collapse" id="collapseVoucher" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                            <nav className="sb-sidenav-menu-nested nav">
                                <Link className="nav-link" to='/voucher-option'>
                                    Voucher Option
                                </Link>
                                <Link className="nav-link" to='/voucher-user'>
                                    Voucher User
                                </Link>
                            </nav>
                        </div>
                        <Link className="nav-link" to='/listAccount'>
                            <div className="sb-nav-link-icon"><i className="fa fa-user-circle-o" aria-hidden="true"></i></div>
                            Account
                        </Link>
                        <Link className="nav-link" to='/Staff'>
                            <div className="sb-nav-link-icon"><i className="fa fa-user-o" aria-hidden="true"></i></div>
                            Staff
                        </Link>
                        <Link className="nav-link" to='/schedule'>
                            <div className="sb-nav-link-icon"><i className="fa fa-user-o" aria-hidden="true"></i></div>
                            Schedule
                        </Link>
                        <Link className="nav-link" to='/booking'>
                            <div className="sb-nav-link-icon"><i className="fa fa-heart" aria-hidden="true"></i></div>
                            Booking
                        </Link>
                    </div>
                </div>
                <div className="sb-sidenav-footer">
                    <div className="small">Logged in as:</div>
                    Start Bootstrap
                </div>
            </nav>
        </div>
    )
}

export default SlideNav