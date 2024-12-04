import {Row} from 'antd';
import {toast} from "react-toastify";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {ProductServiceApi} from "../../service/api/product_service/Product"

const Product = () => {
    const {id} = useParams();
    const [product, setProduct] = useState({});

    useEffect(() => {
        ProductServiceApi.detail(id)
            .then(res => {
                const content = res?.data?.data?.content;
                if (content) {
                    const parsedContent = JSON.parse(content);
                    setProduct(parsedContent);
                }
            })
            .catch((err) => {
                toast.error(err?.response?.data?.message)
            })
    }, []);

    return (
        <>
            <Row
                style={{
                    height: "65vh",
                    width: "100vw",
                    backgroundImage: `url(${product?.cover_image})`,
                    backgroundSize: "cover", // Đảm bảo ảnh bao phủ toàn bộ khung
                    backgroundPosition: "center", // Canh giữa ảnh
                    backgroundRepeat: "no-repeat" // Không lặp lại ảnh
                }}
            >
            </Row>

        </>
    )
}

export default Product;