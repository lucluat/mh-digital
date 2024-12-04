import {request} from "../../../utils/request";

export class ProductServiceApi {

    static URL = "user/product";

    static fetchAll = () => {
        return request({
            method: "GET",
            url: `/${this.URL}`,
        });
    };

    static detail = (id) => {
        return request({
            method: "GET",
            url: `/${this.URL}/${id}`,
        });
    };

}