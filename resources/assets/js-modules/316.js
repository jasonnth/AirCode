__d(function(n,t,e,r){"use strict";function i(n,t){if(-1===t.indexOf(n))throw new TypeError(o(n,t))}function o(n,t){var e='Unknown event type "'+n+'". ';return e+="Known event types: "+t.join(", ")+"."}var c=t(317),u={addValidation:function(n,t){var e=Object.keys(t),r=Object.create(n);return c(r,{emit:function(t,r,o,c,u,a,f){return i(t,e),n.emit.call(this,t,r,o,c,u,a,f)}}),r}};e.exports=u},316);