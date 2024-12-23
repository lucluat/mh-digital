import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import UserFragment from "./layout/user/UserFragment";
import Home from "./page/home/Home";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import Introduction from "./page/introduction/Introduction";
import Recruit from "./page/recruit/Recruit";
import News from "./page/news/News";
import Contact from "./page/contact/Contact";
import Product from "./page/product/Product";
import NotFound from "./component/404";
import BadRequest from "./component/400";
import Forbidden from "./component/403";
import UnAuthorized from "./component/401";
function App() {
    return (
        <div className="App">
            <ToastContainer
                position="top-right"
                autoClose={3000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="light"
            />
            <BrowserRouter basename={"/"}>
                <Routes>
                    <Route path="/404" element={<NotFound/>} />
                    <Route path="/400" element={<BadRequest/>} />
                    <Route path="/403" element={<Forbidden/>} />
                    <Route path="/401" element={<UnAuthorized/>} />
                    {/*<Route path="/not-authorization" key="not-authorization" element={<NotAuthorized />} />*/}
                    <Route
                        path="/"
                        element={
                            <UserFragment>
                                <Home/>
                            </UserFragment>
                        }
                    />
                    <Route
                        path="/introduction"
                        element={
                            <UserFragment>
                                <Introduction/>
                            </UserFragment>
                        }
                    />
                    <Route
                        path="/product/:id"
                        element={
                            <UserFragment>
                                <Product/>
                            </UserFragment>
                        }
                    />
                    <Route
                        path="/contact"
                        element={
                            <UserFragment>
                                <Contact/>
                            </UserFragment>
                        }
                    />
                    <Route
                        path="/recruit"
                        element={
                            <UserFragment>
                                <Recruit/>
                            </UserFragment>
                        }
                    />
                    <Route
                        path="/news"
                        element={
                            <UserFragment>
                                <News/>
                            </UserFragment>
                        }
                    />
                </Routes>
            </BrowserRouter>
            <ToastContainer/>
        </div>
    );
}

export default App;
