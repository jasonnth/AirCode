__d(function(e,r,t,n){function a(e){return!!e&&"function"==typeof e.then}function o(e,r){if(e&&e[r]&&"function"==typeof e[r])try{for(var t=arguments.length,n=Array(t>2?t-2:0),a=2;a<t;a++)n[a-2]=arguments[a];e[r].apply(e,n)}catch(e){}}function p(e,r,t){var n,a=t.promise,p=t.type,s=t.payload,u=t.meta,y=i(),f=s;e({type:p,payload:s,meta:babelHelpers.extends({},u,(n={},babelHelpers.defineProperty(n,l.KEY.LIFECYCLE,l.LIFECYCLE.START),babelHelpers.defineProperty(n,l.KEY.TRANSACTION,y),n))}),o(u,"onStart",s,r);var c=function(t){var n;return e({type:p,payload:t,meta:babelHelpers.extends({},u,(n={startPayload:f},babelHelpers.defineProperty(n,l.KEY.LIFECYCLE,l.LIFECYCLE.SUCCESS),babelHelpers.defineProperty(n,l.KEY.TRANSACTION,y),n))}),o(u,"onSuccess",t,r),o(u,"onFinish",!0,r),{success:!0,payload:t}},E=function(t){var n;return e({type:p,payload:t,error:!0,meta:babelHelpers.extends({},u,(n={startPayload:f},babelHelpers.defineProperty(n,l.KEY.LIFECYCLE,l.LIFECYCLE.FAILURE),babelHelpers.defineProperty(n,l.KEY.TRANSACTION,y),n))}),o(u,"onFailure",t,r),o(u,"onFinish",!1,r),{success:!1,payload:t}};return a.then(c,E)}var l=r(668),s=0,i=function(){return".p"+(s+=1)},u=function(e){return function(r){return function(t){return null==t?null:a(t.promise)?p(e.dispatch,e.getState,t):r(t)}}};t.exports=u},669);