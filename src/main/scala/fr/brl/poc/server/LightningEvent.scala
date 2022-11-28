package fr.brl.poc
package server
import java.time.Instant

sealed trait LightningEvent
case class Lightning(lat: Double, lng: Double, date: Instant) extends LightningEvent
case object NoLightning                                   extends LightningEvent
case object LightningEnd                                  extends LightningEvent
