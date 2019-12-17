[![Build Status](https://travis-ci.com/lostlemon/haal-centraal-brp-bevragen.svg?branch=master)](https://travis-ci.com/lostlemon/haal-centraal-brp-bevragen)


# haal-centraal-brp-bevragen
Haal Centraal BRP bevragen, de referentie implementatie

Install
-------

Build and run the container, then point your browser at: http://localhost:8080/

Build the container
-------------------

```
git clone git@github.com:lostlemon/haal-centraal-brp-bevragen.git
cd haal-centraal-brp-bevragen
docker build -t lostlemon/haalcentraal .
```


Run the container
-----------------

```
docker run -p 8080:8080 -it lostlemon/haalcentraal
```

