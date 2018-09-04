import React, { Component } from 'react';
import PropTypes from 'prop-types';

import './Department.css';

export default class Departments extends Component {
  static propTypes = {
    name: PropTypes.string.isRequired,
    testsVisible: PropTypes.bool,
    tests: PropTypes.array,
    onClick: PropTypes.func,
  }

  render() {
    const { name, testsVisible, tests, onClick } = this.props;

    const icon = testsVisible ? '–' : '+';

    return (
      <section className="department">
        <h3 className="department__heading">
          <button className="department__button" onClick={onClick}>
            <span className="department__icon">{icon}</span>
            {name}
          </button>
        </h3>
        {testsVisible && (
          <table className="table" cellSpacing="0">
            <thead>
              <tr>
                <th>Auðkenni</th>
                <th>Námskeið</th>
                <th>Fjöldi</th>
                <th>Dagsetning</th>
              </tr>
            </thead>
            <tbody>
              {tests.map(test => (
                <tr key={test.course}>
                  <td>{test.course}</td>
                  <td>{test.name}</td>
                  <td>{test.students}</td>
                  <td>{test.date}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>
    );
  }
}
