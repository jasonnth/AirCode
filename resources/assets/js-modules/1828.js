__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var n=t(412),a=babelHelpers.interopRequireDefault(n),o=t(271),i=(babelHelpers.interopRequireDefault(o),t(44)),u=t(1826),s=babelHelpers.interopRequireDefault(u),c=t(410),p=babelHelpers.interopRequireDefault(c),b=t(379),d=babelHelpers.interopRequireDefault(b),f={phone:a.default.string.isRequired},h={},y=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props.phone,t=e?e.replace(/\D/g,""):null,l="tel:"+t;return babelHelpers.jsx(s.default,{rausch:!0,icon:"belo",title:d.default.phrase("Are you sure?",null,"page title for host to call airbnb and cancel booked trip instance"),subtitle:d.default.phrase("Guests make travel plans around your experience, so canceling could significantly affect their schedule. To cancel, you\u2019ll need to contact our customer experience team.",null,"text to describe the consequence of canceling a booked trip instance"),primaryTitle:d.default.phrase("Call Airbnb",null,"button title for host to click and call airbnb"),onPrimaryClick:function(){return i.Linking.openURL(l)},secondaryTitle:d.default.phrase("Go back",null,"button title to close the modal"),onSecondaryClick:function(){return p.default.dismiss()}})},t}(o.Component);r.default=y,y.propTypes=f,y.defaultProps=h},1828);