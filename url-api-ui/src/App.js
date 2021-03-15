import './App.css';
import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import UrlServicePostCall from './services/UrlServicePostCall';


function App() {
	const [url, setUrl] = useState("");
	const [shorturl,setshorturl] = useState("");
	const [errorMsg,seterrorMsg] = useState("");

	return(
			<div className= "container">

			<Form onSubmit={handleSubmit}>
			<Form.Group size="lg" controlId="url">
			<Form.Label>Please Enter Long URL here!</Form.Label>
			<Form.Control
			autoFocus
			type="text"
			value={url}
			onChange={(e) => setUrl(e.target.value)}
			/>
			<br></br>
			<Button block size="lg" type="submit" disabled={!validateForm()}>
			Make URL Shoten
			</Button>
			<br></br>
			<div className="error_msg" >{errorMsg}</div>
			<br></br>
			<label disabled={!validateUrl()} > Hello, Short URL is   < a href={shorturl}> {shorturl} </a></label>
			</Form.Group>

			</Form>

			</div>

			);

	function validateUrl(){
		return shorturl.length>0;
	}

	function validateForm() {
		return url.length > 0;
	}

	function validUrl(url){
		var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
				'((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ // domain name
				'((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
				'(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
				'(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
				'(\\#[-a-z\\d_]*)?$','i'); // fragment locator
		return !!pattern.test(url);
	}

	function handleSubmit(event) {
		event.preventDefault();
		console.log(url);
		if(validUrl(url)){
			UrlServicePostCall.getShortUrl(url).then( value =>  setshorturl(value.data) );
			console.log("shorturl==="+shorturl);
			seterrorMsg("");
		} else {
			seterrorMsg("Please enter a valid url");
		}
	}
}

export default App;
