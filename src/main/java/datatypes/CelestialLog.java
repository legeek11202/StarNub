//package datatypes;
//
//import network.StarboundStream;
//
///**
// * Created by Daniel on 6/6/2014.
// */
//public class CelestialLog {
//
//        public List<LogSector> Sectors { get; set; }
//
//        public List<SystemCoordinate> Visited { get; set; }
//
//        public SystemCoordinate CurrentSystem { get; set; }
//
//        public WorldCoordinate CurrentLocation { get; set; }
//
//        public WorldCoordinate HomeCoordinate { get; set; }
//
//        public CelestialLog()
//        {
//            Sectors = new List<LogSector>();
//            Visited = new List<SystemCoordinate>();
//            CurrentLocation = new WorldCoordinate();
//            HomeCoordinate = new WorldCoordinate();
//        }
//
//        public static CelestialLog FromStream(StarboundStream stream)
//        {
//
//            CelestialLog log = new CelestialLog();
//
//            byte[] logDat = stream.ReadUInt8Array();
//
//            using (MemoryStream ms = new MemoryStream(logDat))
//            {
//                using (StarboundStream s = new StarboundStream(ms))
//                {
//
//                    uint visited = s.ReadUInt32();
//
//                    for (int i = 0; i < visited; i++)
//                    {
//                        log.Visited.Add(s.ReadSystemCoordinate());
//                    }
//
//                    uint sectors = s.ReadUInt32();
//
//                    for (int i = 0; i < sectors; i++)
//                    {
//                        log.Sectors.Add(new LogSector { SectorName = s.ReadString(), Unknown = s.ReadBoolean() })
//                    }
//
//                    s.ReadUInt8(); //unknown
//
//                    log.CurrentSystem = s.ReadSystemCoordinate();
//                    log.CurrentLocation = s.ReadWorldCoordinate();
//                    log.HomeCoordinate = s.ReadWorldCoordinate();
//
//                }
//            }
//
//            return log;
//
//        }
//
//        public void WriteTo(StarboundStream stream)
//        {
//        }
//
//    }
//}
