__d(function(e,t,r,n){"use strict";var i=t(86),o=t(89),s=t(13),l=function(e){function r(){babelHelpers.classCallCheck(this,r);var t=new o,n=babelHelpers.possibleConstructorReturn(this,e.call(this,t));return n.sharedSubscriber=t,n}return babelHelpers.inherits(r,e),r.prototype._nativeEventModule=function(e){if(e){if(0===e.lastIndexOf("statusBar",0))return t(90);if(0===e.lastIndexOf("keyboard",0))return t(92);if("appStateDidChange"===e||"memoryWarning"===e)return t(94)}return null},r.prototype.addListener=function(t,r,n){var i=this._nativeEventModule(t);return i?i.addListener(t,r,n):e.prototype.addListener.call(this,t,r,n)},r.prototype.removeAllListeners=function(t){var r=this._nativeEventModule(t);r&&t?r.removeAllListeners(t):e.prototype.removeAllListeners.call(this,t)},r.prototype.removeSubscription=function(t){t.emitter!==this?t.emitter.removeSubscription(t):e.prototype.removeSubscription.call(this,t)},r}(i);l=new l,s.registerCallableModule("RCTDeviceEventEmitter",l),r.exports=l},85);