__d(function(t,e,i,l){Object.defineProperty(l,"__esModule",{value:!0});var a=e(44),n=Object.getPrototypeOf(a.Animated.ValueXY),u=1,d=function(t){function e(i){babelHelpers.classCallCheck(this,e);var l=babelHelpers.possibleConstructorReturn(this,t.call(this)),n=i||{latitude:0,longitude:0,latitudeDelta:0,longitudeDelta:0};return l.latitude=n.latitude instanceof a.Animated.Value?n.latitude:new a.Animated.Value(n.latitude),l.longitude=n.longitude instanceof a.Animated.Value?n.longitude:new a.Animated.Value(n.longitude),l.latitudeDelta=n.latitudeDelta instanceof a.Animated.Value?n.latitudeDelta:new a.Animated.Value(n.latitudeDelta),l.longitudeDelta=n.longitudeDelta instanceof a.Animated.Value?n.longitudeDelta:new a.Animated.Value(n.longitudeDelta),l._listeners={},l}return babelHelpers.inherits(e,t),e.prototype.setValue=function(t){this.latitude._value=t.latitude,this.longitude._value=t.longitude,this.latitudeDelta._value=t.latitudeDelta,this.longitudeDelta._value=t.longitudeDelta},e.prototype.setOffset=function(t){this.latitude.setOffset(t.latitude),this.longitude.setOffset(t.longitude),this.latitudeDelta.setOffset(t.latitudeDelta),this.longitudeDelta.setOffset(t.longitudeDelta)},e.prototype.flattenOffset=function(){this.latitude.flattenOffset(),this.longitude.flattenOffset(),this.latitudeDelta.flattenOffset(),this.longitudeDelta.flattenOffset()},e.prototype.__getValue=function(){return{latitude:this.latitude.__getValue(),longitude:this.longitude.__getValue(),latitudeDelta:this.latitudeDelta.__getValue(),longitudeDelta:this.longitudeDelta.__getValue()}},e.prototype.__attach=function(){this.latitude.__addChild(this),this.longitude.__addChild(this),this.latitudeDelta.__addChild(this),this.longitudeDelta.__addChild(this)},e.prototype.__detach=function(){this.latitude.__removeChild(this),this.longitude.__removeChild(this),this.latitudeDelta.__removeChild(this),this.longitudeDelta.__removeChild(this)},e.prototype.stopAnimation=function(t){this.latitude.stopAnimation(),this.longitude.stopAnimation(),this.latitudeDelta.stopAnimation(),this.longitudeDelta.stopAnimation(),t&&t(this.__getValue())},e.prototype.addListener=function(t){var e=this,i=String(u++),l=function(){t(e.__getValue())};return this._listeners[i]={latitude:this.latitude.addListener(l),longitude:this.longitude.addListener(l),latitudeDelta:this.latitudeDelta.addListener(l),longitudeDelta:this.longitudeDelta.addListener(l)},i},e.prototype.removeListener=function(t){this.latitude.removeListener(this._listeners[t].latitude),this.longitude.removeListener(this._listeners[t].longitude),this.latitudeDelta.removeListener(this._listeners[t].latitudeDelta),this.longitudeDelta.removeListener(this._listeners[t].longitudeDelta),delete this._listeners[t]},e.prototype.spring=function(t){var e=[];return t.hasOwnProperty("latitude")&&e.push(a.Animated.timing(this.latitude,babelHelpers.extends({},t,{toValue:t.latitude}))),t.hasOwnProperty("longitude")&&e.push(a.Animated.timing(this.longitude,babelHelpers.extends({},t,{toValue:t.longitude}))),t.hasOwnProperty("latitudeDelta")&&e.push(a.Animated.timing(this.latitudeDelta,babelHelpers.extends({},t,{toValue:t.latitudeDelta}))),t.hasOwnProperty("longitudeDelta")&&e.push(a.Animated.timing(this.longitudeDelta,babelHelpers.extends({},t,{toValue:t.longitudeDelta}))),a.Animated.parallel(e)},e.prototype.timing=function(t){var e=[];return t.hasOwnProperty("latitude")&&e.push(a.Animated.timing(this.latitude,babelHelpers.extends({},t,{toValue:t.latitude}))),t.hasOwnProperty("longitude")&&e.push(a.Animated.timing(this.longitude,babelHelpers.extends({},t,{toValue:t.longitude}))),t.hasOwnProperty("latitudeDelta")&&e.push(a.Animated.timing(this.latitudeDelta,babelHelpers.extends({},t,{toValue:t.latitudeDelta}))),t.hasOwnProperty("longitudeDelta")&&e.push(a.Animated.timing(this.longitudeDelta,babelHelpers.extends({},t,{toValue:t.longitudeDelta}))),a.Animated.parallel(e)},e}(n);l.default=d},1656);