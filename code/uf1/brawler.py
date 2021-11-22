import os
import re
import requests
import shutil
import getpass
import configargparse
import json
from urllib.parse import urlparse

class Crawler:
	base_url = 'https://www.hackerrank.com/'
	user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36 Edg/85.0.564.63'
	login_url = base_url + 'auth/login'

	def __init__(self):
		self.session = requests.Session()
		self.options = {}

	def parse_script(self):
		p = configargparse.ArgParser(default_config_files=['./user.yaml'])
		p.add('-u', '--username', help='hackerrank account username')
		p.add('-p', '--password', help='hackerrank account password')

		self.options = p.parse_args()

	def authenticate(self):
		username = self.options.username or input('Hackerrank Username: ')
		password = self.options.password or getpass.getpass('Hackerrank Password: ')

		resp = self.session.post(self.login_url, auth=(username, password), headers={'user-agent': self.user_agent})
		data = resp.json()
		if data['status']:
			#self.cookies = self.session.cookies.get_dict()
			self.headers = resp.request.headers
			print(self.headers)
		return data['status']

	def get(self, url):
		resp = self.session.get(url, headers=self.headers)
		if resp.ok:
			return resp.json()
		return {}

	def getfile(self, url):
		print("testcases " + url)
		resp = self.session.get(url, headers=self.headers, stream=True)
		if not resp.ok:
			print("no se pudo descargar " + url)
		return self.session.get(url, headers=self.headers, stream=True)



def main():
	crawler = Crawler()
	crawler.parse_script()
	if not crawler.authenticate():
		print('Auth was unsuccessful. Exiting the program')
		exit(1)

	pattern = re.compile(r'img src="(.*?)"')

	challenges_url = "https://www.hackerrank.com/rest/administration/challenges"

	total = crawler.get(challenges_url)['total']
	limit = 10000

	down = {}
	for i in range(1): #(total//limit+1):
		url = "https://www.hackerrank.com/rest/administration/challenges?limit={}&offset={}".format(limit, i*limit)

		resp = crawler.get(url)

		for model in resp['models']:
			down[model['slug']] = model['id']


	for slug in down:
		#print("dowloading... " + slug)
		url = "https://www.hackerrank.com/rest/administration/challenges/{}".format(down[slug])

		admch = crawler.get(url)

		if 'model' in admch and 'contest_challenge_associations' in admch['model'] and len(admch['model']['contest_challenge_associations']) > 0:
			url = "https://www.hackerrank.com/rest/contests/{}/challenges/{}/".format(admch['model']['contest_challenge_associations'][0]['slug'], down[slug])
			testurl = "https://www.hackerrank.com/rest/contests/{}/challenges/{}/download_testcases".format(admch['model']['contest_challenge_associations'][0]['slug'], down[slug])
		else:
			url = "https://www.hackerrank.com/rest/contests/previewdummycontest/challenges/{}/".format(down[slug])
			testurl = "https://www.hackerrank.com/rest/contests/previewdummycontest/challenges/{}/download_testcases".format(down[slug])

		#print("dowloading... " + url)

		resp = crawler.get(url)

		if 'model' in resp:
			slugdir = "chs/{}/".format(slug)
			os.makedirs(slugdir, exist_ok=True)

			with open(slugdir + "{}.json".format(slug), 'w') as outfile:
				json.dump(resp, outfile)

			for src in re.findall(pattern, resp['model']['body_html']):
				response = requests.get(src, stream=True)
				imagefile = slugdir + os.path.basename(urlparse(src).path)
				with open(imagefile, 'wb') as out_file:
					shutil.copyfileobj(response.raw, out_file)
				del response
				resp['model']['body_html'] = resp['model']['body_html'].replace(src, os.path.basename(urlparse(src).path))

			with open(slugdir + "{}.html".format(slug), 'w') as outfile:
				outfile.write(resp['model']['body_html'])

			with open(slugdir + "{}.json".format(slug), 'w') as outfile:
				json.dump(resp, outfile)

			response = crawler.getfile(testurl)
			with open(slugdir + "{}-testcases.zip".format(slug), 'wb') as outfile:
				response.raw.decode_content = True
				shutil.copyfileobj(response.raw, outfile)
			del response
		else:
			print("Sin respuesta de : " + slug + " : " + url)

if __name__ == "__main__":
	main()
