import {
    Popover,
    PopoverButton,
    PopoverGroup,
    PopoverPanel,
} from '@headlessui/react'
import {ChevronDownIcon} from '@heroicons/react/20/solid'
import {useEffect, useState} from "react";
import logo from "../../assets/images/logo.png";
import {Link} from "react-router-dom";
import {ProductServiceApi} from "../../service/api/product_service/Product"
import {toast} from "react-toastify";
import Footer from "../../component/Footer"

const UserFragment = ({children}) => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        ProductServiceApi.fetchAll().then(products => {
            setProducts(products?.data?.data);
        }).catch(() => {
            toast.error("Có lỗi xảy ra khi lấy danh sách dịch vụ và giải pháp");
        });
    }, [])

    return (
        <>
            <header className="fixed top-0 bg-white/95 left-0 right-0 bg-white shadow z-50">
                <nav aria-label="Global" className="mx-auto flex max-w-7xl items-center justify-between p-6  lg:px-8">
                    <div className=" ">
                        <Link
                            style={{fontSize: "16px"}}
                            to="/">
                            <img
                                style={{
                                    // width: "90%",
                                    height: "10%",
                                }}
                                alt=""
                                src={logo}
                                className="h-8 w-auto"
                            />
                        </Link>
                    </div>
                    <PopoverGroup className="hidden lg:flex lg:gap-x-8">
                        <Link
                            style={{fontSize: "16px"}}
                            to="/" className="text-sm/6 font-semibold text-gray-900">
                            TRANG CHỦ
                        </Link>
                        <Link
                            style={{fontSize: "16px"}}
                            to="/introduction" className="text-sm/6 font-semibold text-gray-900">
                            GIỚI THIỆU
                        </Link>
                        <Popover className="relative">
                            <PopoverButton
                                style={{fontSize: "16px"}}
                                className="flex items-center gap-x-1 text-sm/6 font-semibold text-gray-900">
                                DỊCH VỤ & GIẢI PHÁP
                                <ChevronDownIcon aria-hidden="true" className="size-5 flex-none text-gray-400"/>
                            </PopoverButton>
                            <PopoverPanel
                                transition
                                className="absolute -left-8 top-full z-10 mt-3 w-screen max-w-md overflow-hidden rounded-3xl bg-white shadow-lg ring-1 ring-gray-900/5 transition data-[closed]:translate-y-1 data-[closed]:opacity-0 data-[enter]:duration-200 data-[leave]:duration-150 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                                <div className="p-4">
                                    {products.map((item) => (
                                        <div
                                            key={item.id}
                                            className="group relative flex items-center gap-x-6 rounded-lg p-4 text-sm/6 hover:bg-gray-50"
                                        >
                                            <div className="flex-auto">
                                                <Link
                                                    style={{fontSize: "16px"}}
                                                    to={"/product/" + item.id}
                                                    className="block font-semibold text-gray-900">
                                                    {item.title}
                                                    <span className="absolute inset-0"/>
                                                </Link>
                                                <p className="mt-1 text-gray-600">{item.description}</p>
                                            </div>
                                        </div>
                                    ))}
                                </div>
                            </PopoverPanel>
                        </Popover>

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
                    </PopoverGroup>
                </nav>
            </header>

            {children}
            <Footer firstProduct={products[0]?.id} />
        </>
    )
}

export default UserFragment;