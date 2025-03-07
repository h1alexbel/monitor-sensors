# Monitor Sensors, a RESTful API

[![mvn](https://github.com/h1alexbel/monitor-sensors/actions/workflows/mvn.yml/badge.svg)](https://github.com/h1alexbel/monitor-sensors/actions/workflows/mvn.yml)
[![codecov](https://codecov.io/gh/h1alexbel/monitor-sensors/graph/badge.svg?token=CzyzEWswO1)](https://codecov.io/gh/h1alexbel/monitor-sensors)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/h1alexbel/monitor-sensors/blob/master/LICENSE.txt)

Simple CRUD application for sensors monitoring.

## Interact with API

In this API you can:

* Create Sensor: `POST /sensors`
* Read all Sensors: `GET /sensors`
* Read Sensor by ID: `GET /sensors/123`:
* Update Sensor: `PUT /sensors/123`
* Delete Sensor: `DELETE /sensors/123`
* Search Sensors by model and name: `GET /sensors/search?input=fo`

The [JSON]-format is the following:

```json
{
  "id": 123,
  "description": "Some description",
  "location": "CA",
  "model": "ff",
  "name": "foo",
  "range": {
    "from": 1,
    "to": 25,
    "validRange": true
  },
  "type": "VOLTAGE",
  "unit": "VOLTAGE"
}
```

Also, user management is possible:

* Register user: `POST /users`

If you would like to run it in [Docker] (assuming you are in the root project dir):

```bash
docker compose up -d
```

## How to contribute?

Make sure that you have Java 11+ installed on your system, then fork this
repository, make changes, send us a [pull request][guidelines]. We will
review your changes and apply them to the `master` branch shortly, provided
they don't violate our quality standards. To avoid frustration, before sending
us your pull request please run full build:

```bash
mvn clean install -Pqulice
```

[Docker]: https://www.docker.com
[guidelines]: https://www.yegor256.com/2014/04/15/github-guidelines.html
[JSON]: https://en.wikipedia.org/wiki/JSON
