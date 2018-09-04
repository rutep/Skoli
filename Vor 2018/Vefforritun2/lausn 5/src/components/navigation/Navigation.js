import React, { Component } from 'react';
import { NavLink } from 'react-router-dom'

import './Navigation.css';

const url = process.env.REACT_APP_SERVICE_URL;

export default class Navigation extends Component {
  state = {
    data: null,
    loading: true,
    error: false,
  }

  async componentDidMount() {
    try {
      const data = await this.fetchData();
      this.setState({ data, loading: false });
    } catch (e) {
      console.error('Error fetching navigation', e);
      this.setState({ error: true, loading: false });
    }
  }

  async fetchData() {
    const response = await fetch(url);
    const data = await response.json();
    return data;
  }

  render() {
    const { data, loading, error } = this.state;

    if (loading) {
      return (<div>Hleð inn sviðum...</div>);
    }

    if (error) {
      return (<div>Villa við að hlaða inn sviðum</div>);
    }

    return (
      <nav className="navigation">
        <ul className="navigation__list">
          {data.schools && data.schools.map((department) => (
            <li className="navigation__item" key={department.name}>
              <NavLink
                to={department.link}
                className="navigation__link"
                activeClassName="navigation__link--active"
              >
                {department.name}
              </NavLink>
            </li>
          ))}
        </ul>
      </nav>
    );
  }
}
