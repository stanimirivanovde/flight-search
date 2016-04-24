import requests

client_id = 'V1:evcqowtjs83fpl4z:DEVCENTER:EXT'
client_secret = '7b8KaIgO'

credentials = ":".join([part.encode('base64').strip() 
	for part in (client_id, client_secret)]
	).encode('base64').strip()

url = 'https://api.test.sabre.com/v2/auth/token'
headers = {'Authorization': 'Basic ' + credentials}
params = {'grant_type': 'client_credentials'}

r = requests.post(url, headers=headers, data=params)
assert r.status_code is 200, 'Oops...'
token = r.json()
print(token)


headers = {'Authorization': 'Bearer ' + token[u'access_token']}
#endpoint = 'https://api.test.sabre.com/v1/lists/supported/countries'
#r = requests.get(endpoint, headers=headers)
#assert r.status_code is 200, 'Oops...'

#print (r.json())

# Find lowest fares for the past 2 weeks

#endpoint = "https://api.sabre.com/v1/historical/shop/flights/fares?origin=PHL&destination=ORL&departuredate=2016-02-10&returndate=2016-02-15&pointofsalecountry=US"
endpoint = "https://api.sabre.com/v1/historical/shop/flights/fares?origin=DFW&destination=ORL&departuredate=2016-02-21&returndate=2016-02-28&pointofsalecountry=US"
r = requests.get(endpoint, headers=headers)
assert r.status_code is 200, 'Oops...'

print (r.json())
