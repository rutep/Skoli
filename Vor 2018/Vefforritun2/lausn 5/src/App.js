import React, { Component } from 'react';
import Helmet from 'react-helmet';
import { Route, Link, Switch } from 'react-router-dom'

import './App.css';

import Home from './components/home';
import School from './components/school';
import Navigation from './components/navigation';
import NotFound from './components/not-found';

class App extends Component {
  render() {

    return (
      <main className="app">
        <Helmet defaultTitle="Próftöflur" titleTemplate="%s – Próftöflur" />

        <header className="app__header">
          <h1 className="app__heading"><Link to="/">Próftöflur</Link></h1>
          <Navigation />
        </header>

        <section>
          <Switch>
            <Route exact path="/" component={Home}/>
            <Route exact path="/:school" component={School} />
            <Route component={NotFound} />
          </Switch>
        </section>
      </main>
    );
  }
}

export default App;
