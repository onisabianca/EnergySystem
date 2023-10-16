import React from 'react';

import BackgroundImg from '../commons/images/energyBackground.png';

import {Button, Container, Jumbotron, Label} from 'reactstrap';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1920px",
    backgroundImage: `url(${BackgroundImg})`
};

const textStyle = {color: 'blue'};

const textStyle2 = {color: 'white', marginTop: "100px", marginLeft: "100px"};

const buttonStyle = {
    marginTop: "100px",
    marginLeft: "600px"
};


class Home extends React.Component {


    render() {

        return (

            <div>
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>
                        <h1 className="display-3" style={textStyle}>ENERGY SYSTEM MANAGEMENT PLATFORM</h1>
                        <h2 className="display" style={textStyle2}>This is an online platform for energy management
                        that allows both employees and customers to login and monitor their systems. Users can easily
                        and quickly view data related to their personal devices, as well as the hourly consumption.  </h2>
                                        <Container style={buttonStyle}>
                                        <form action="/login" class="inline">
                                            <Button variant="primary" size="lg">
                                                Login
                                            </Button>{' '}
                                            </form>
                                        </Container>

                    </Container>
                </Jumbotron>
            </div>
        )
    };
}

export default Home
