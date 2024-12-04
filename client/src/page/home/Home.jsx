import { Button } from 'antd';
import {toast} from "react-toastify";
const Home = () => {
    return (
        <>
            <div ></div>
            <Button type="primary" onClick={()=>{
                toast.success("hehe");
            }}>test toast</Button>
            home
        </>
    )
}

export default Home;