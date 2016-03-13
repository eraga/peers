/*
    This file is part of Peers, a java SIP softphone.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2007, 2008, 2009, 2010 Yohann Martineau
*/


package net.sourceforge.peers.sip.core.useragent;

import net.sourceforge.peers.sdp.Codec;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;

public interface SipListener {

    void onRegistering(SipRequest sipRequest);

    void onRegisterSuccessful(SipResponse sipResponse);

    void onRegisterFailed(SipResponse sipResponse);

    void onIncomingCall(SipRequest sipRequest, SipResponse provResponse);

//    /**
//     * When an incoming call is cancelled
//     */
//    void onCallCancelled(UserAgent ua);
//
//    /**
//     * When an ougoing call has been accepted
//     */
//    void onCallAccepted(UserAgent ua);
//
//    /**
//     * When an ougoing call is stated to be in progress
//     */
//    void onCallProgress(UserAgent ua);
//
//    /**
//     * When an ougoing call is remotly onRinging
//     */
//    void onCallRinging(UserAgent ua);
//
//
//    /**
//     * When an ougoing call has been refused or timeout
//     */
//    void onCallFailed(UserAgent ua, String reason);


    void onRemoteHangup(SipRequest sipRequest);

    void onRinging(SipResponse sipResponse);

    void onCalleePickup(SipResponse sipResponse);

    void onError(SipResponse sipResponse);


    /**
     * When a new media session is started
     */
    void onMediaSessionStarted(UserAgent ua, Codec codec);

    /**
     * When a media session is stopped
     */
    void onMediaSessionStopped(UserAgent ua);


}
