#!/bin/sed
# Adds header specification for other more urls
/<response-header name="x-powered-by-header" header-name="X-Powered-By" header-value="Undertow\/1"\/>/a\
<!-- AUTOMATICALLY  GENERATED AT STARTUP -->\
<response-header name="Access-Control-Allow-Origin" header-name="Access-Control-Allow-Origin" header-value="http://localhost:4200"/>\
<response-header name="Access-Control-Allow-Methods" header-name="Access-Control-Allow-Methods" header-value="GET, POST, OPTIONS, PUT"/>\
<response-header name="Access-Control-Allow-Headers" header-name="Access-Control-Allow-Headers" header-value="accept, authorization, content-type, x-requested-with"/>\
<response-header name="Access-Control-Allow-Credentials" header-name="Access-Control-Allow-Credentials" header-value="true"/>\
<response-header name="Access-Control-Max-Age" header-name="Access-Control-Max-Age" header-value="1"/>\
<!-- END OF AUTOMATICALLY  GENERATED AT STARTUP -->
/<filter-ref name="x-powered-by-header"\/>/a\
<!-- AUTOMATICALLY  GENERATED AT STARTUP -->\
<filter-ref name="Access-Control-Allow-Origin"/>\
<filter-ref name="Access-Control-Allow-Methods"/>\
<filter-ref name="Access-Control-Allow-Headers"/>\
<filter-ref name="Access-Control-Allow-Credentials"/>\
<filter-ref name="Access-Control-Max-Age"/>\
<!-- END OF AUTOMATICALLY  GENERATED AT STARTUP -->
