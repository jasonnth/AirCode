__d(function(e,t,r,o){function a(e){return[R["view-"+e.size],e.style]}function n(e){return[R["text-"+e.size],{color:e.color,backgroundColor:e.backgroundColor}]}Object.defineProperty(o,"__esModule",{value:!0}),o.ICON_MAP=o.IconPropTypes=o.IconPropType=void 0;var i=t(412),l=babelHelpers.interopRequireDefault(i),s=t(271),c=babelHelpers.interopRequireDefault(s),p=t(44),u=t(559),d=t(752),h="android"===p.Platform.OS,b={accessible:61697,"add-listing":61698,add:61699,"air-conditioning":61700,"air-mattress":61701,"alert-alt":61704,"alert-fill":61705,alert:61706,apartment:61707,archive:61708,"arrow-down-alt":61709,"arrow-down":61710,"arrow-left-alt":61711,"arrow-left":61857,"arrow-reply":61713,"arrow-right-alt":61714,"arrow-right":61715,"arrow-up-alt":61716,"arrow-up":61717,balloons:61718,"ban-circle":61719,"bar-chart":61720,bars:61721,bathtub:61722,bell:61723,bellhop:61724,belo:61725,"belo-heart":61726,birdhouse:61727,boat:61728,bolt:61729,book:61730,bullseye:61731,"bunk-bed":61732,bus:61733,cabin:61734,"calendar-alt":61735,calendar:61736,camera:61737,camper:61738,car:61739,"caret-down":61740,"caret-left":61741,"caret-right":61742,"caret-up":61743,castle:61744,cat:61745,checkbox:61746,"chevron-down":61747,"chevron-left":61858,"chevron-right":61909,"chevron-up":61750,city:61751,cloud:61753,code:61754,cog:61755,"comment-negative":61756,"comment-positive":61757,comment:61758,comments:61759,couch:61760,crib:61761,"cup-alt":61762,cup:61763,"currency-brl":61764,"currency-chf":61765,"currency-czk":61766,"currency-eur":61767,"currency-gbp":61768,"currency-huf":61769,"currency-idr":61770,"currency-ils":61771,"currency-inr":61772,"currency-jpy":61773,"currency-krw":61774,"currency-myr":61775,"currency-nok":61776,"currency-php":61777,"currency-pln":61778,"currency-rub":61779,"currency-thb":61780,"currency-try":61781,"currency-usd":61782,"currency-vnd":61783,"currency-zar":61784,"dates-price":61785,"description-alt":61786,description:61787,desktop:61788,dog:61789,doorman:61790,"double-bed":61791,download:61792,dryer:61793,edit:61794,elevator:61795,"emergency-exit":61796,"entire-place":61797,"envelope-inbound":61798,"envelope-outbound":61799,envelope:61800,essentials:61801,events:61802,"external-link":61803,eye:61804,"facebook-messenger":61805,facebook:61806,family:61807,filter:61808,filters:61808,"fire-alarm":61810,"fire-alt":61811,"fire-extinguisher":61812,fire:61813,fireplace:61814,flag:61815,"floor-mattress":61816,flower:61817,friends:61818,"full-screen":61819,"gas-valve":61820,gift:61821,globe:61822,"google-plus":61823,"group-alt":61824,group:61825,"guide-book":61826,gym:61827,"hair-dryer":61828,hairdryer:61829,hammock:61830,handshake:61831,hangers:61832,haze:61833,"heart-alt":61836,heart:61837,heating:61838,"home-safety":61839,home:61840,"host-guarantee":61841,"host-home-alt":61842,"host-home":61843,"hot-tub":61844,"id-card-alt":61845,"id-card-back":61846,"id-card":61847,instagram:61848,"instant-book-alt":61849,"instant-book-wide":61850,intercom:61851,internet:61852,"invite-friends":61853,iron:61854,key:61855,laptop:61856,liability:61859,lifesaver:61860,"light-bulb-alt":61861,"light-bulb":61862,lighthouse:61863,linkedin:61864,"list-ul":61865,listings:61866,location:61867,lock:61868,map:61869,"map-marker-alt":61870,"map-marker":61871,match:61872,meal:61873,meetups:61874,minus:61875,"money-deposit":61876,"money-none":61877,moon:61878,"nav-left":h?61857:61858,"ok-alt":61879,"ok-fill":61880,ok:61881,overview:61882,"paper-plane":61883,parking:61884,"partly-cloudy":61885,passport:61886,paw:61887,"phone-alt":61888,"phone-android":61889,"phone-inbound":61890,"phone-outbound":61891,phone:61892,"photo-upload":61893,picture:61894,pinterest:61895,plane:61896,pool:61897,"private-room":61898,"question-alt":61899,question:61900,rain:61901,"real-bed":61902,"recently-viewed":61903,record:61904,refresh:61905,remove:61752,reorder:61907,repeat:61908,rooms:61910,"sad-face":61911,search:61912,"secure-user":61913,sesame:61914,shampoo:61915,"share-android":61916,"share-ios":61917,share:h?61916:61917,"shared-room":61919,"shopping-bag":61920,"shopping-basket":61921,"single-bed":61922,smoking:61923,snow:61924,"sofa-bed":61925,"speaks-zh":61926,"spray-bottle":61927,"star-alt":61928,"star-circled":61929,"star-half":61930,star:61931,stats:61932,"stopwatch-alt":61933,stopwatch:61934,suitcase:61935,sun:61936,tablet:61937,"tag-alt":61938,tag:61939,tent:61940,"thumbs-down-alt":61941,"thumbs-down":61942,"thumbs-up-alt":61943,"thumbs-up":61944,"time-dark":61945,time:61946,"toddler-bed":61947,train:61948,trash:61949,tv:61950,twitter:61951,unlock:61952,upload:61953,"user-circle":61954,user:61955,"verified-id":61956,"video-none":61957,"video-pause-alt":61958,"video-pause":61959,"video-play-alt":61960,"video-play":61961,video:61962,viewfinder:61963,vk:61964,volume:61965,washer:61966,"water-bed":61967,webcam:61968,wechat:61969,weibo:61970,whatsapp:61971,wifi:61972,wind:61973,youtube:61974},f=l.default.oneOf(Object.keys(b)),y=[8,9,10,12,14,16,17,19,22,24,28,32,40,48],m={name:f.isRequired,size:l.default.oneOf(y),color:p.ColorPropType,backgroundColor:p.ColorPropType,style:p.View.propTypes.style,needsViewWrap:l.default.bool},w=l.default.oneOfType([f,l.default.shape(m)]),g={size:16,color:d.color.textDark,needsViewWrap:!0},v=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.shouldComponentUpdate=function(e){return!(0,u.shallowEqualsWithStyle)(this.props,e,{style:!0})},t.prototype.render=function(){var e=this.props,t=e.size,r=e.style,o=e.name,a=e.needsViewWrap,n={color:this.props.color,backgroundColor:this.props.backgroundColor},i=babelHelpers.jsx(p.Text,{allowFontScaling:!1,style:[R["text-"+t],n,!a&&r]},void 0,String.fromCharCode(b[o]));return a?babelHelpers.jsx(p.View,{style:[R["view-"+t],r]},void 0,i):i},t}(s.Component),k={8:h?[1,2,-.25]:[1,0,0],10:h?[1,2,-.5]:[1,1,0],12:h?[1,2,-1]:[1,1,0],14:h?[1,2,-1]:[1,1,0],16:h?[2,2,-1]:[2,2,1],17:h?[2,2,-1]:[2,2,1],19:h?[2,2,-1]:[2,2,1],22:h?[2,3,-1.5]:[2,2,0],24:h?[2,3,-1.5]:[2,2,0],28:h?[2,3,-1.5]:[2,2,0],32:h?[2,6,-3]:[2,2,1],40:h?[3,6,-3]:[0,0,0],48:h?[3,6,-3]:[3,2,1]},C=function(e){return e in k?k[e]:[0,0,0]},x={};y.forEach(function(e){x["view-"+e]={width:e+C(e)[0],height:e+C(e)[0],backgroundColor:"transparent"},x["text-"+e]={fontFamily:"Airglyphs",fontSize:e,lineHeight:e,width:e+C(e)[0],height:e+C(e)[1],marginTop:C(e)[2],textAlign:"center"}});var R=p.StyleSheet.create(x);v.propTypes=m,v.defaultProps=g;var T=function(e){function t(r){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return o.setViewRef=o.setViewRef.bind(o),o.setTextRef=o.setTextRef.bind(o),o}return babelHelpers.inherits(t,e),t.prototype.shouldComponentUpdate=function(e){return!(0,u.shallowEqualsWithStyle)(this.props,e,{style:!0})},t.prototype.setNativeProps=function(e){this.viewRef&&this.viewRef.setNativeProps({style:a(e)}),this.textRef&&this.textRef.text&&this.textRef.text.setNativeProps({style:n(e)})},t.prototype.setViewRef=function(e){this.viewRef=e},t.prototype.setTextRef=function(e){this.textRef=e},t.prototype.render=function(){return c.default.createElement(p.View,{ref:this.setViewRef,style:a(this.props)},c.default.createElement(p.Text,{ref:this.setTextRef,allowFontScaling:!1,style:n(this.props)},String.fromCharCode(b[this.props.name])))},t}(s.Component);T.propTypes=m,v.Animated=p.Animated.createAnimatedComponent(T),o.default=v,o.IconPropType=f,o.IconPropTypes=w,o.ICON_MAP=b},815);