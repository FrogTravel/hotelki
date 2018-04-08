#/usr/bin/python2.7

import flask
# import psycopg2

app = flask.Flask(__name__)
app.config.update(
		DATABASE={
	  'dbname': 'hotelki',
	  'username': 'postgres',
	  'password': 'postgres',
	  'host': '77.108.109.83',
	  'port': 25539
	}
)

def connect_db():
	# conn = psycopg2.connect(**app.config['DATABASE'])
	return conn

def get_db():
	if not hasattr(g, 'postgre_db'):
		g.postgre_db = connect_db()
	return g.postgre_db

def init_db():
	db = get_db()

	with app.open_resource('schema.sql', mode='r') as f:
		db.cursor().execurescript(f.read())

	db.commit()

@app.cli.command('initdb')
def initdb_cmd():
	init_db()
	print("Initialized db")

@app.cli.command('dropdb')
def dropdb_cmd():
	drop_db()
	print("Dropped db")

# @app.teardown_appcontext
# def close_db(error):
# 	if hasattr(g, 'postgre_db'):
# 		g.postgre_db.close()

@app.route('/')
def show():
	comments = [{'author': 'John Doe', 'content': 'Lorem ipsum'}, {'author': 'Mark', 'content': 'Я люблю кофер'}]
	stories = [{'id': 1, 'title': 'a', 'likes': 15, 'dislikes': 11, 'description': 'lorem ipsum ist dalor'}, 
						{'id': 2, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогие друзья'}, 
						{'id': 3, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогой друщь'}, 
						{'id': 4, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Друсь'}, 
						{'id': 5, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогие друзья'}, 
						{'id': 6, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогие друзья'}
						]
	return flask.render_template('index.html', stories=stories, comments=comments)