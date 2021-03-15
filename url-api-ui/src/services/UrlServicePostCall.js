import axios from 'axios';

const RestCall = "http://localhost:8080/url/longUrl";


class UrlServicePostCall {
    getShortUrl(url){
        console.log("url===>"+url);
        return axios({
            method: 'post',
            url: RestCall,
            data: url,
            headers: { "Content-Type": 'text/plain' }
        })
      
    }

}

export default new UrlServicePostCall()