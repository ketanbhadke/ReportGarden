import React,  { Component } from 'react';
import ReactDOM from 'react-dom';

export class SearchRepository extends React.Component {

    constructor(props){
        super(props);
        this.state = {keyword: '', myStr: ''};
        // alert('cfvf');
        //this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        console.log("inside compomnent did mount");
        // fetch('http://localhost:8090/search/repositories/' + this.state.keyword)
        //   .then(response => response.json())
        //   .then(res => this.setState({ myStr: res.myStr }));
        // console.log("end compomnent did mount");
        fetch('http://localhost:8090/search/repositories/Certs')
            .then(res => res.json())
            .then(res => {console.log("res "+ res[0].name)});
      }
    

    handleChange(event){
        this.setState({keyword: event.target.value});
    }

    handleSubmit = () => {
        // alert('A name was submitted: ' + this.state.keyword);
            console.log("handleSublit");
        //    var x = fetch('http://localhost:8090/search/repositories/' + this.state.keyword);
        //    console(x);
        // console.log("inside handle submit");
        //    fetch('http://localhost:8090/search/repositories/Certs')
        //     .then(res => res.json())
        //     .then(res => {console.log("reposnse: " + res)});
                
            // var y = this.state.myStr;
            // console.log("response : " + )
            console.log("response handle : "+ this.state.myStr);
            // alert("alerting"+ this.state.myStr);
            
    }


    render(){
        return (
            <div>
                <form onSubmit={this.handleSubmit.bind(this)}>
                    <label>
                        Search Repository:
                        <input type="text" name="name" placeholder="Enter keyword here ..." onChange={ this.handleChange }/>
                    </label>
                    <input type="submit" value="Submit" />



                </form>
            </div>
        )
    }
}