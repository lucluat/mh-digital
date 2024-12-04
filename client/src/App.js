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
