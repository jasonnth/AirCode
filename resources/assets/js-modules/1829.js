__d(function(e,t,n,r){Object.defineProperty(r,"__esModule",{value:!0});var i=t(412),s=babelHelpers.interopRequireDefault(i),a=t(271),p=(babelHelpers.interopRequireDefault(a),t(44)),l=t(790),c=t(379),o=babelHelpers.interopRequireDefault(c),d=t(828),u=babelHelpers.interopRequireDefault(d),I=t(410),b=babelHelpers.interopRequireDefault(I),f=t(787),h=babelHelpers.interopRequireDefault(f),T=t(910),x=babelHelpers.interopRequireDefault(T),g=t(422),E=babelHelpers.interopRequireDefault(g),m=t(1805),y=babelHelpers.interopRequireDefault(m),H=t(1788),v=babelHelpers.interopRequireDefault(H),R=t(1752),q=t(1813),D=babelHelpers.interopRequireDefault(q),_=t(1798),C=t(1770),j=babelHelpers.interopRequireWildcard(C),w=t(1803),B={experienceInstancesById:s.default.object.isRequired,experienceTemplatesById:s.default.object.isRequired,isTripInstanceLoading:s.default.bool.isRequired,tripInstancesById:s.default.object.isRequired,tripTemplatesById:s.default.object.isRequired,selectedTripInstanceId:s.default.number.isRequired,selectedExperienceInstanceId:s.default.number.isRequired},L={},N=function(e){var t=(0,w.experienceInstancesSelector)(e),n=(0,w.experienceTemplatesSelector)(e),r=(0,w.tripInstancesSelector)(e),i=(0,w.tripTemplatesSelector)(e);return{experienceInstancesById:t,experienceTemplatesById:n,isTripInstanceLoading:(0,w.availabilitySelector)(e).isTripInstanceLoading,tripInstancesById:r,tripTemplatesById:i}},S=function(e){function t(n){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,n));return r.showChangeTime=r.showChangeTime.bind(r),r.showChangeLocation=r.showChangeLocation.bind(r),r}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){this.props.selectedExperienceInstanceId||b.default.pop()},t.prototype.getExperienceInstance=function(){var e=this.props;return e.experienceInstancesById[e.selectedExperienceInstanceId]},t.prototype.getExperienceTemplate=function(){var e=this.getExperienceInstance();return this.props.experienceTemplatesById[e.associatedExperienceTemplateId]},t.prototype.getTripInstance=function(){var e=this.getExperienceInstance();return this.props.tripInstancesById[e.associatedTripInstanceId]},t.prototype.getTripTemplate=function(){var e=this.getTripInstance();return this.props.tripTemplatesById[e.associatedTripTemplateId]},t.prototype.showChangeTime=function(){var e=this.props.selectedTripInstanceId,t=this.getExperienceInstance();t&&(b.default.present(j.EDIT_EXPERIENCE_INSTANCE_TIME,{selectedExperienceInstanceId:t.id,selectedTripInstanceId:e}),(0,_.logEvent)("ViewExperienceInstance",{experience_instance_id:t.id,operation:"show_change_time"}))},t.prototype.showChangeLocation=function(){var e=this.getExperienceInstance();e&&(b.default.push(j.EDIT_EXPERIENCE_INSTANCE_LOCATION,{selectedExperienceInstanceId:e.id}),(0,_.logEvent)("ViewExperienceInstance",{experience_instance_id:e.id,operation:"show_change_location"}))},t.prototype.renderDetails=function(){var e=this.props.selectedTripInstanceId,t=this.getTripTemplate(),n=this.getExperienceInstance(),r=this.getExperienceTemplate();return n?babelHelpers.jsx(p.View,{},void 0,babelHelpers.jsx(x.default,{heavy:!0,first:!0,title:o.default.phrase("Details",null,"button or link title for click to view details page")}),babelHelpers.jsx(D.default,{tripTemplate:t,experienceInstance:n,experienceTemplate:r,selectedTripInstanceId:e})):null},t.prototype.renderGuests=function(){var e=this.getTripInstance(),t=this.getTripTemplate();return babelHelpers.jsx(y.default,{tripInstance:e,tripTemplate:t})},t.prototype.render=function(){var e=this.props.isTripInstanceLoading,t=this.getExperienceInstance(),n=this.getExperienceTemplate(),r=e||!t.detailsFetched||!n.detailsFetched,i=(0,R.getDescription)(n).name||o.default.phrase("(Noname)",null,"placeholder for empty experience title"),s=(0,R.getExperienceTemplatePhoto)(n);return babelHelpers.jsx(p.View,{style:P.container},void 0,babelHelpers.jsx(h.default,{},void 0,babelHelpers.jsx(u.default,{image:s,title:i}),r&&babelHelpers.jsx(v.default,{}),!r&&this.renderDetails(),!r&&this.renderGuests()))},t}(a.Component);S.propTypes=B,S.defaultProps=L;var P=E.default.create(function(){return{container:{flex:1}}});r.default=(0,l.connect)(N)(S)},1829);