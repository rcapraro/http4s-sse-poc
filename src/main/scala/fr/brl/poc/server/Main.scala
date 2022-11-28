package fr.brl.poc
package server

import cats.{Applicative, ApplicativeThrow}
import cats.data.{Kleisli, OptionT}
import cats.effect.*
import fs2.*
import cats.effect.IO.asyncForIO
import cats.syntax.option.*
import com.comcast.ip4s.{host, port}
import fs2.concurrent.SignallingRef
import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.middleware.CORS
import org.typelevel.log4cats.SelfAwareStructuredLogger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import sttp.model.sse.ServerSentEvent
import sttp.tapir.*
import sttp.tapir.redoc.RedocUIOptions
import sttp.tapir.redoc.bundle.RedocInterpreter
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.http4s.Http4sServerInterpreter

import scala.concurrent.duration.DurationInt

object Main extends IOApp.Simple {

  private val ContextPath: List[String]   = List("api", "v1")
  private val DocPathPrefix: List[String] = "redoc" :: Nil

  private val Logger: SelfAwareStructuredLogger[IO] = Slf4jLogger.getLogger[IO]

  override def run: IO[Unit] = EmberServerBuilder
    .default[IO]
    .withHost(host"0.0.0.0")
    .withPort(port"8080")
    .withHttpApp {

      val redocEndpoints: List[ServerEndpoint[Any, IO]] =
        RedocInterpreter(redocUIOptions = RedocUIOptions.default.contextPath(ContextPath).pathPrefix(DocPathPrefix))
          .fromEndpoints[IO](List(Routes.GetSSEData), "SSE data Api", "1.0.0")

      val lightningService = new LightningService[IO]()

      val routes = Http4sServerInterpreter[IO]().toRoutes(Routes.GetSSEData.serverLogicSuccess(_ => IO(lightningService.startPublisher)))

      routes.orNotFound
    }
    .build
    .useForever
}
