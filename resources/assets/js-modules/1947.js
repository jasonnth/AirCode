__d(function(e,n,t,i){function r(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:p,n=arguments[1],t=n.meta,i=n.payload;switch(n.type){case s.CACHE_DATA:return e.set("cached",!0);case s.CLEAR_CACHE:return e.set("cached",!1);case s.REMOVE_ENTITIES:var r=i.entities,a=i.entitiesIdMapping,_=i.lastSyncedAt,o=e;return(r||[]).forEach(function(e){o=o.deleteIn(["entities"].concat(e))}),(a||[]).forEach(function(e){var n=["entitiesIdMapping"].concat(e.slice(0,e.length-1)),t=e[e.length-1];o=o.updateIn(n,function(e){if(e){var n=[].concat(babelHelpers.toConsumableArray(e));return n.splice(e.indexOf(t),1),n}return[]})}),(_||[]).forEach(function(e){o=o.deleteIn(["lastSyncedAt"].concat(e))}),o;case s.UPDATE_ENTITIES:var l=t.entity,N=t.idMappingEntity,d=t.id,C=t.updateType,S=(0,I.mergeIn)(e,["entities",l],i.entities[l]);if(N){var u=["entitiesIdMapping",N,d],A=i.result;S=C===c.UPDATE_TYPE_MERGE?(0,I.mergeIn)(S,u,A):S.setIn(u,A),S=S.setIn(["lastSyncedAt",N,d],Date.now())}return S;case s.UPDATE_CAN_FETCH_MORE:return e.set("canFetchMore",i);case s.SYNC_TRIP_SCHEDULES:var M=i.entities,f=i.result,P=e.getIn(["entities",c.ENTITY_TRIP_SCHEDULE]).sort(function(e,n){return(0,T.compareDataForSection)(c.SECTION_UPCOMING_AND_PAST,e,n)}).slice(0,f.length),O=e;return P.forEach(function(e){var n=e.confirmationCode,t=["entities",c.ENTITY_TRIP_SCHEDULE,n];O=-1===f.indexOf(n)?O.deleteIn(t):O.setIn(t,M[c.ENTITY_TRIP_SCHEDULE][n])}),O=O.setIn(["loading",c.ENTITY_SECTION,c.SECTION_UPCOMING_AND_PAST,c.LOADING_KEY_DEFAULT],!1),O=O.setIn(["lastSyncedAt",c.ENTITY_SECTION,c.SECTION_UPCOMING_AND_PAST],Date.now());case s.REHYDRATE:var R=i.itinerary,y=R.entities,D=R.entitiesIdMapping;return e.withMutations(function(e){var n=e;return y&&Object.keys(y).forEach(function(e){n=n.setIn(["entities",e],new E.Map(y[e]))}),D&&Object.keys(D).forEach(function(e){n=n.setIn(["entitiesIdMapping",e],new E.Map(D[e]))}),n});case s.SET_LOADING:var b=t.entity,w=t.id,H=t.loadingKey;return e.setIn(["loading",b,w,H||c.LOADING_KEY_DEFAULT],i);case s.CLEAR_TRIP_SCHEDULES:return e.setIn(["entities",c.ENTITY_TRIP_SCHEDULE],new E.Map).setIn(["entitiesIdMapping",c.ENTITY_SECTION],new E.Map).setIn(["lastSyncedAt",c.ENTITY_SECTION,c.SECTION_PENDING],null).setIn(["lastSyncedAt",c.ENTITY_SECTION,c.SECTION_UPCOMING_AND_PAST],null);case s.ACCEPT_TRIP_INVITE:return e.update("acceptedTripInvites",function(e){return e.push(i)});case s.SET_VERIFICATION_FLAGS:return(0,I.mergeIn)(e,["verification"],i);case s.SET_VISIBLE_TRIP_SCHEDULE:return e.set("visibleTripScheduleConfirmationCode",i);default:return e}}Object.defineProperty(i,"__esModule",{value:!0});var a;i.default=r;var E=n(417),I=n(1948),T=n(1946),s=n(1945),c=n(1818),_=(0,E.Record)((a={},babelHelpers.defineProperty(a,c.ENTITY_CARD,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_CANCELLATION_POLICY,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_EXPERIENCE_RESERVATION,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_HOME_RESERVATION,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_HOME_RESERVATION_RECEIPT,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_PLACE_RESERVATION,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_IMMERSION,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_RECOMMENDATION_CARD,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_SECTION,new E.Map),babelHelpers.defineProperty(a,c.ENTITY_TRIP_SCHEDULE,new E.Map),a),"Entities"),p=new E.Map({acceptedTripInvites:new E.List,cached:!1,canFetchMore:!1,entities:new _,entitiesIdMapping:new _,lastSyncedAt:new _,loading:new _,verification:new E.Map,visibleTripScheduleConfirmationCode:null})},1947);