__d(function(e,r,t,l){Object.defineProperty(l,"__esModule",{value:!0});var n=r(412),i=babelHelpers.interopRequireDefault(n),s=r(271),a=babelHelpers.interopRequireDefault(s),u=r(44),o=r(410),p=babelHelpers.interopRequireDefault(o),b=r(1032),c=babelHelpers.interopRequireDefault(b),f=r(379),d=babelHelpers.interopRequireDefault(f),H=r(422),h=babelHelpers.interopRequireDefault(H),q=r(1482),C=babelHelpers.interopRequireWildcard(q),v=r(2422),R=r(1483),j={faqs:i.default.object},A={faqs:{}},D=function(e){function r(t){babelHelpers.classCallCheck(this,r);var l=babelHelpers.possibleConstructorReturn(this,e.call(this,t));return l.renderQuestions=l.renderQuestions.bind(l),l}return babelHelpers.inherits(r,e),r.onClickArticle=function(e){(0,v.logArticleClick)(e.id),p.default.present(C.AUTHENTICATED_WEBVIEW,{url:(0,R.getWebUrl)(e.url)})},r.prototype.renderQuestions=function(){var e=[];return Object.values(this.props.faqs).forEach(function(t){e.push(babelHelpers.jsx(c.default,{title:t.question,onPress:function(){return r.onClickArticle(t)}},t.id))}),e},r.prototype.render=function(){return babelHelpers.jsx(u.ScrollView,{},void 0,babelHelpers.jsx(u.View,{style:T.container},void 0,babelHelpers.jsx(p.default.Screen,{barType:p.default.Screen.BAR_TYPE.BASIC,title:d.default.phrase("Choose an article",null,"nav bar title for suggested articles")}),this.renderQuestions()))},r}(a.default.Component);l.default=D,D.defaultProps=A,D.propTypes=j;var T=h.default.create(function(e){return{container:{paddingTop:7*e.bp}}})},2426);