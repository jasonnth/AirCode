__d(function(e,r,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}function n(e,r){var t={};for(var a in e)r.indexOf(a)>=0||Object.prototype.hasOwnProperty.call(e,a)&&(t[a]=e[a]);return t}function u(e,r,t){for(var a=r.length-1;a>=0;a--){var o=r[a](e);if(o)return o}return function(r,a){throw new Error("Invalid value of type "+typeof e+" for "+t+" argument when connecting component "+a.wrappedComponentName+".")}}function i(e,r){return e===r}function p(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},r=e.connectHOC,t=void 0===r?c.default:r,a=e.mapStateToPropsFactories,o=void 0===a?h.default:a,p=e.mapDispatchToPropsFactories,s=void 0===p?P.default:p,d=e.mergePropsFactories,v=void 0===d?E.default:d,g=e.selectorFactory,m=void 0===g?O.default:g;return function(e,r,a){var p=arguments.length>3&&void 0!==arguments[3]?arguments[3]:{},c=p.pure,d=void 0===c||c,P=p.areStatesEqual,g=void 0===P?i:P,h=p.areOwnPropsEqual,E=void 0===h?f.default:h,q=p.areStatePropsEqual,O=void 0===q?f.default:q,S=p.areMergedPropsEqual,w=void 0===S?f.default:S,y=n(p,["pure","areStatesEqual","areOwnPropsEqual","areStatePropsEqual","areMergedPropsEqual"]),M=u(e,o,"mapStateToProps"),T=u(r,s,"mapDispatchToProps"),_=u(a,v,"mergeProps");return t(m,l({methodName:"connect",getDisplayName:function(e){return"Connect("+e+")"},shouldHandleStateChanges:Boolean(e),initMapStateToProps:M,initMapDispatchToProps:T,initMergeProps:_,pure:d,areStatesEqual:g,areOwnPropsEqual:E,areStatePropsEqual:O,areMergedPropsEqual:w},y))}}a.__esModule=!0;var l=Object.assign||function(e){for(var r=1;r<arguments.length;r++){var t=arguments[r];for(var a in t)Object.prototype.hasOwnProperty.call(t,a)&&(e[a]=t[a])}return e};a.createConnect=p;var s=r(796),c=o(s),d=r(799),f=o(d),v=r(800),P=o(v),g=r(803),h=o(g),m=r(804),E=o(m),q=r(805),O=o(q);a.default=p()},798);