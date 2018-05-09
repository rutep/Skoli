import React, { Component } from 'react';

import './Home.css';

const url = process.env.REACT_APP_SERVICE_URL ;

export default class Home extends Component {
  state = {
    loading: true,
    data: {},
    error: false,
  }

  async componentDidMount() {
    try {
      const data = await this.fetchData();
      this.setState({ data, loading: false });
    } catch (error) {
      console.error('Error fetching stats', error);
      this.setState({ error: true, loading: false });
    }
  }

  fetchData = async () => {
    const response = await fetch(`${url}stats`);
    const data = await response.json();
    return data;
  }

  render() {
    const { data, loading } = this.state;

    if (loading) {
      return (<div>Hleð inn gögnum...</div>);
    }

    const { numTests = 0, averageStudents = 0, min = 0, max = 0, numStudents = 0 } = data.stats;

    return (
      <div className="home">
        <h2 className="home__heading">Tölfræði</h2>
        <dl className="home__stats">
          <dt className="home__term">Fjöldi prófa</dt>
          <dd>{numTests}</dd>
          <dt className="home__term">Fjöldi nemenda í öllum prófum</dt>
          <dd>{numStudents}</dd>
          <dt className="home__term">Meðalfjöldi nemenda í prófi</dt>
          <dd>{averageStudents}</dd>
          <dt className="home__term">Minnsti fjöldi nemenda í prófi</dt>
          <dd>{min}</dd>
          <dt className="home__term">Mesti fjöldi nemenda í prófi</dt>
          <dd>{max}</dd>
        </dl>
      </div>
    );
  }
}
