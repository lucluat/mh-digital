import {request} from "../../../utils/request";

export class HomeServiceApi {

    static URL = "user/home";

    static fetchAll = (id) => {
        return request({
            method: "GET",
            url: `/${this.URL}/${id}`,
        });
    };

}