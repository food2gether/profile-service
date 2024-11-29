# Profiles Service

## GET `/api/v1/profiles/`

Returns a list of all profiles.

### Headers
_None_

### Parameters
| Key    | Type     | Required | Description             |
|--------|----------|----------|-------------------------|
| search | `String` | `false`  | The search query string |

### Response
<table>
  <thead>
    <tr>
      <th>Status Code</th>
      <th>Description</th>
      <th>Example Response Body</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>200</td>
      <td>Profile found</td>
      <td>
        <pre lang="json">
{
  "success": true,
  "data": [
    {
      "id": 1731095302112,
      "name": "max_mustermann",
      "displayname": "Max Mustermann",
      "contact": [
        {
          "displayname": "E-Mail",
          "value": "max.mustermann@example.com"
        },
        {
          "displayname": "Phone",
          "value": "+49 1568 483234"
        }
      ]
    },
    {
      "id": 1099871596193,
      "name": "phro",
      "displayname": "Dr. Philipp Rohde",
      "contact": [
        {
          "displayname": "E-Mail",
          "value": "rhode@fh-aachen.com"
        },
        {
          "displayname": "Discord",
          "value": "phro#3865"
        }
      ]
    }
  ]
}
        </pre>
      </td>
    </tr>
  </tbody>
</table>


### Example Request
```shell
curl --request GET https://food2gether.com/api/v1/profiles/
```

## GET `/api/v1/profiles/:id`

Returns the profile with the given ID/username.

### Headers
_None_

### Parameters
| Key  | Type           | Required | Description                  |
|------|----------------|----------|------------------------------|
| `id` | `int`/`String` | `true`   | The ID of the profile to get |

### Response
<table>
  <thead>
    <tr>
      <th>Status Code</th>
      <th>Description</th>
      <th>Example Response Body</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>200</td>
      <td>Account found</td>
      <td>
        <pre lang="json">
{
  "success": true,
  "data": {
    "id": 1731095302112,
    "name": "max_mustermann",
    "displayname": "Max Mustermann",
    "contact": [
      {
        "displayname": "E-Mail",
        "value": "max.mustermann@example.com"
      },
      {
        "displayname": "Phone",
        "value": "+49 1568 483234"
      }
    ]
  }
}
        </pre>
      </td>
    </tr>
    <tr>
      <td>404</td>
      <td>Not found</td>
      <td>
        <pre lang="json">
{
  "success": false,
  "error": {
    "code": 404,
    "message_key": "account.notfound"
  }
}
        </pre>
      </td>
    </tr>
  </tbody>
</table>


### Example Request
```shell
curl --request GET https://food2gether.com/api/v1/accounts/1731095302112
```

## PUT `/api/v1/accounts/`

Creates/Updates a profile.

### Headers
| Key             | Description                        |
|-----------------|------------------------------------|
| `Authorization` | `Basic <email:password \| base64>` |
| `Content-Type`  | `application/json`                 |

### Parameters
When `id` is omitted, a new profile is created. In this case all other arguments are required. When `id` is provided, the profile with the given ID is updated. \
The `name` field is ignored when updating a profile.

| Key           | Type                                          | Required | Description                                |
|---------------|-----------------------------------------------|----------|--------------------------------------------|
| `id`          | `int`/`String`                                | `false`  | The ID/username of the profile to update   |
| `name`        | `String`                                      | `false`  | A unique username                          |
| `displayname` | `String`                                      | `false`  | The display name of the user               |
| `contact`     | `Array<{displayname: String, value: String}>` | `false`  | An array of contact information            |

### Response
<table>
  <thead>
    <tr>
      <th>Status Code</th>
      <th>Description</th>
      <th>Example Response Body</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>200</td>
      <td>Updated</td>
      <td>
        <pre lang="json">{
  "success": true,
  "data": {
    "id": 1731095302112
  }
}</pre>
      </td>
    </tr>
    <tr>
      <td>201</td>
      <td>Created</td>
      <td>
        <pre lang="json">{
  "success": true,
  "data": {
    "id": 1731095302112
  }
}</pre>
      </td>
    </tr>
    <tr>
      <td>400</td>
      <td>Missing arguments</td>
      <td>
        <pre lang="json">{
  "success": false,
  "error": {
    "code": 400,
    "message_key": "request.missingarguments"
  }
}</pre>
      </td>
    </tr>
    <tr>
      <td>401</td>
      <td>Unauthorized</td>
      <td>
        <pre lang="json">{
  "success": false,
  "error": {
    "code": 401,
    "message_key": "authorization.failed"
  }
}</pre>
      </td>
    </tr>
    <tr>
      <td>403</td>
      <td>Username already exists</td>
      <td>
        <pre lang="json">{
  "success": false,
  "error": {
    "code": 403,
    "message_key": "user.exists"
  }
}</pre>
      </td>
    </tr>
    <tr>
      <td>409</td>
      <td>Profile already associated with account</td>
      <td>
        <pre lang="json">{
  "success": false,
  "error": {
    "code": 409,
    "message_key": "account.exists"
  }
}</pre>
      </td>
    </tr>
  </tbody>
</table>


### Example Request
```shell
curl --request PUT https://food2gether.com/api/v1/accounts/ \
     --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQK' \
     --header 'Content-Type: application/json' \
     --data @- << EOF
{
  "name": "max_mustermann",
  "displayname": "Max Mustermann",
  "contact": [
    {
      "displayname": "MS Teams",
      "value": "mamu"
    }
  ]
}
EOF
```

## DELETE `/api/v1/accounts/:id/`

Deletes a profile.

### Headers
| Key             | Description                        |
|-----------------|------------------------------------|
| `Authorization` | `Basic <email:password \| base64>` |

### Parameters
| Key           | Type                                          | Required | Description                              |
|---------------|-----------------------------------------------|----------|------------------------------------------|
| `id`          | `int`/`String`                                | `true`   | The ID/username of the profile to update |

### Response
<table>
  <thead>
    <tr>
      <th>Status Code</th>
      <th>Description</th>
      <th>Example Response Body</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>200</td>
      <td>Deleted</td>
      <td>
        <pre lang="json">
{
  "success": true,
  "data": null
}
        </pre>
      </td>
    </tr>
    <tr>
      <td>401</td>
      <td>Unauthorized</td>
      <td>
        <pre lang="json">
{
  "success": false,
  "error": {
    "code": 401,
    "message_key": "authorization.failed"
  }
}
        </pre>
      </td>
    </tr>
  </tbody>
</table>


### Example Request
```shell
curl --request PUT https://food2gether.com/api/v1/accounts/ \
     --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQK' \
```