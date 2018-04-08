import psycopg2

conn = psycopg2.connect(dbname='hotelki', user='postgres', password='13524', host='77.108.109.83', port='30030')
c = conn.cursor()
c.execute('select * from Markers;')
print("hell")
conn.close()
