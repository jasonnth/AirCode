__d(function(t,i,e,n){"use strict";function a(){this.reinitializeTransaction()}var s=i(136),r=i(144),c=i(23),o={initialize:c,close:function(){u.isBatchingUpdates=!1}},p={initialize:c,close:s.flushBatchedUpdates.bind(s)},d=[p,o];babelHelpers.extends(a.prototype,r,{getTransactionWrappers:function(){return d}});var l=new a,u={isBatchingUpdates:!1,batchedUpdates:function(t,i,e,n,a,s){var r=u.isBatchingUpdates;return u.isBatchingUpdates=!0,r?t(i,e,n,a,s):l.perform(t,null,i,e,n,a,s)}};e.exports=u},217);