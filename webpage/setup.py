from setuptools import setup

setup(
	name='flaskr',
	packages=['files'],
	include_package_data=True,
	install_requires=[
		'flask',
		'psycopg2',
	],
)