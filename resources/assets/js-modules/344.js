__d(function(t,n,a,e){"use strict";function r(t){var n=t.navigationState,a=t.scene,e=n.index===a.index,r=e?1:0,i=a.index>n.index?1:-1,o=e?0:1e6*i;return{opacity:r,transform:[{translateX:o},{translateY:o}]}}function i(t){var n=t.layout,a=t.position,e=t.scene;if(!n.isMeasured)return r(t);var i=e.index,s=[i-1,i,i+1],u=n.initWidth,d=o.isRTL?[-u,0,u]:[u,0,-u];return{opacity:1,shadowColor:"transparent",shadowRadius:0,transform:[{scale:1},{translateX:a.interpolate({inputRange:s,outputRange:d})},{translateY:0}]}}var o=n(275);a.exports={forHorizontal:i}},344);