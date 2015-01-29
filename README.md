An Example of a Spring CXF JAX-RS app that was two APIS. One public and one private.
======================================================


Key Features
------------

-    Spring Injection
-    JAX-RS
-    CXF
-    Dual API


Getting Started
---------------

To launch the application run the command :

```
jetty:run
```

There will be 2 APIs avaliable:

This API has no security:

http://localhost:8080/external/avaliableExternally

The second API:

http://localhost:8080/internal/avaliableInternally

As a level of 'security' If you send in a request header called "SECURE" with a value of "Special Key" you will be able
to gain access. This security is achieved through the SecureFilter.


