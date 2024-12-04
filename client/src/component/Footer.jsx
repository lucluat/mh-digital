import coppyRight from "../assets/images/copy-right.svg"
import logoDigital from "../assets/images/logo-digital.svg"
import {Link} from "react-router-dom";
import {useEffect} from "react";

const Footer = (prop) => {
    return (
        <div style={{
            backgroundColor: "#ededed00"
        }}>
            <div style={{
                display: "flex",
                justifyContent: "space-around",
                margin: "5%",
            }}>
                <div >
                    <div style={{display: "flex", justifyContent: "center"}}>
                        <img src={logoDigital} alt="logoDigital" width="100px"/>
                    </div>
                    <div style={{display: "flex", justifyContent: "center"}}>
                        <div style={{display: "flex", gap: "3%", marginTop: "5%"}}>
                            <div>
                                <img src={coppyRight} alt="coppyRight"/>
                            </div>
                            <div>
                                <span>Copyright 2020</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div style={{display: "grid", textAlign: "start"}}>
                    <Link to="/"
                          style={{fontSize: "16px"}}
                          className="text-sm/6 font-semibold text-gray-900">
                        TRANG CHỦ
                    </Link>
                    <Link
                        style={{fontSize: "16px"}}
                        to="/introduction" className="text-sm/6 font-semibold text-gray-900">
                        GIỚI THIỆU
                    </Link>
                    <Link
                        style={{fontSize: "16px"}}
                        to={"/product/" + prop.firstProduct} className="text-sm/6 font-semibold text-gray-900">
                        DỊCH VỤ & GIẢI PHÁP
                    </Link>
                    <Link
                        style={{fontSize: "16px"}}
                        to="/news" className="text-sm/6 font-semibold text-gray-900">
                        TIN TỨC
                    </Link>
                    <Link
                        style={{fontSize: "16px"}}
                        to="/recruit" className="text-sm/6 font-semibold text-gray-900">
                        TUYỂN DỤNG
                    </Link>
                    <Link
                        style={{fontSize: "16px"}}
                        to="/contact" className="text-sm/6 font-semibold text-gray-900">
                        LIÊN HỆ
                    </Link>
                </div>
                <div style={{textAlign: "start"}}>
                    <div
                        style={{fontSize: "16px"}}
                        className="text-sm/6 font-semibold text-gray-900">
                        LIÊN HỆ
                    </div>
                    <div>
                        Địa chỉ: 71-73, Trần Thái Tông, Dịch<br/> Vọng, Cầu Giấy, Hà Nội
                    </div>
                    <div>
                        Email: contact@mhdigital.vn
                    </div>
                    <div>
                        Điện thoại: 0968 500 018
                    </div>
                </div>
                <div style={{textAlign: "start"}}>
                    <div
                        style={{fontSize: "16px"}}
                        className="text-sm/6 font-semibold text-gray-900">
                        Đăng ký nhận thông tin
                    </div>
                    <div>
                        Đăng ký email để nhận tin tức và dịch<br/> vụ mới nhất từ chúng tôi
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Footer;