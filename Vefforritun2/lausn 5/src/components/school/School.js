import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Helmet from 'react-helmet';
import { Route, Link } from 'react-router-dom';

import NotFound from '../not-found';
import Department from '../department';

import './School.css';

const url = process.env.REACT_APP_SERVICE_URL;

export default class School extends Component {

  static propTypes = {
    match: PropTypes.shape({
      params: PropTypes.shape({
        school: PropTypes.string,
      }),
    }),
  }

  state = {
    data: null,
    loading: true,
    visibleDepartment: '',
  }

  async componentDidMount() {
    try {
      const data = await this.fetchData();
      this.setState({ data, loading: false });
    } catch (error) {
      console.error('Error fetching school', error);
      this.setState({ error: true, loading: false });
    }
  }

  async componentDidUpdate(prevProps, prevState) {
    if (this.props.match.params.school !== prevProps.match.params.school) {
      this.setState({ loading: true });

      try {
        const data = await this.fetchData();
        this.setState({ data, loading: false });
      } catch (error) {
        console.error('Error fetching school', error);
        this.setState({ error: true, loading: false });
      }
    }
  }

  fetchData = async () => {
    const {
      match: {
        params: {
          school = '',
        } = {},
      } = {},
    } = this.props;

    const response = await fetch(`${url}${school}`);
    const data = await response.json();
    return data;
  }

  clickHandler = (department) => {
    return (e) => {
      e.preventDefault();

      const { visibleDepartment } = this.state;

      if (visibleDepartment === department) {
        this.setState({ visibleDepartment: '' });
      } else {
        this.setState({ visibleDepartment: department });
      }
    }
  }

  render() {
    const { data, loading, visibleDepartment = '' } = this.state;

    if (loading) {
      return (<p>Hleð prófum...</p>);
    }

    const {
      school: {
        heading = '',
        departments = [],
      } = {},
    } = data;

    if (!heading) {
      return (<Route component={NotFound} />);
    }

    return (
      <section className="school">
        <Helmet title={heading} />
        <h2 className="school__heading">{heading}</h2>
        <div className="school__departments">
          {departments.map(department => (
            <Department
              key={department.heading}
              name={`${department.heading}`}
              tests={department.tests}
              testsVisible={department.heading === visibleDepartment}
              onClick={this.clickHandler(department.heading)}
            />
          ))}
        </div>
        <p><Link to="/">Heim</Link></p>
      </section>
    );
  }
}
