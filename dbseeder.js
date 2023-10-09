db = connect( "mongodb://localhost:27017" );
db = db.getSiblingDB('tpride-dev')
db.themeParkRides.insertMany( [
   {
      _id: '1',
      name: 'Rollercoaster',
      description: 'Train ride that speeds you along.',
      thrillFactor: 5,
      vomitFactor: 3
   },
   {
      _id: '2',
      name: 'Log flume',
      description: 'Boat ride with plenty of splashes.',
      thrillFactor: 3,
      vomitFactor: 2
   },
   {
      _id: '3',
      name: 'Teacups',
      description: 'Spinning ride in a giant tea-cup.',
      thrillFactor: 2,
      vomitFactor: 4
   }
] )
