//package datatypes;
//
//import network.StarboundStream;
//
///**
// * Created by Daniel on 6/6/2014.
// */
//
//public class Coordinates2D
//
//    {
//        public int X, Y;
//
//        public Coordinates2D( int x, int y)
//        {
//            X = x;
//            Y = y;
//        }
//
//        public static Coordinates2D operator -(Coordinates2D a)
//        {
//            return new Coordinates2D(-a.X, -a.Y);
//        }
//
//        public static Coordinates2D operator +(Coordinates2D a, Coordinates2D b)
//        {
//            return new Coordinates2D(a.X + b.X, a.Y + b.Y);
//        }
//
//        public static Coordinates2D operator -(Coordinates2D a, Coordinates2D b)
//        {
//            return new Coordinates2D(a.X - b.X, a.Y - b.Y);
//        }
//
//        public static Coordinates2D operator*(Coordinates2D a, Coordinates2D b)
//        {
//            return new Coordinates2D(a.X * b.X, a.Y * b.Y);
//        }
//
//        public static Coordinates2D operator/(Coordinates2D a, Coordinates2D b)
//        {
//            return new Coordinates2D(a.X / b.X, a.Y / b.Y);
//        }
//
//        public static Coordinates2D operator%(Coordinates2D a, Coordinates2D b)
//        {
//            return new Coordinates2D(a.X % b.X, a.Y % b.Y);
//        }
//
//        public static boolean operator!=(Coordinates2D a, Coordinates2D b)
//        {
//            return !a.Equals(b);
//        }
//
//        public static boolean operator==(Coordinates2D a, Coordinates2D b)
//        {
//            return a.Equals(b);
//        }
//
//        public static Coordinates2D operator +(Coordinates2D a,int b)
//        {
//            return new Coordinates2D(a.X + b, a.Y + b);
//        }
//
//        public static Coordinates2D operator -(Coordinates2D a,int b)
//        {
//            return new Coordinates2D(a.X - b, a.Y - b);
//        }
//
//        public static Coordinates2D operator*(Coordinates2D a,int b)
//        {
//            return new Coordinates2D(a.X * b, a.Y * b);
//        }
//
//        public static Coordinates2D operator/(Coordinates2D a,int b)
//        {
//            return new Coordinates2D(a.X / b, a.Y / b);
//        }
//
//        public static Coordinates2D operator%(Coordinates2D a,int b)
//        {
//            return new Coordinates2D(a.X % b, a.Y % b);
//        }
//
//    public double DistanceTo(Coordinates2D other) {
//        return Math.Sqrt(Square((double) other.X - (double) X) +
//                Square((double) other.Y - (double) Y));
//    }
//
//    private double Square(double num) {
//        return num * num;
//    }
//
//    @Override
//    public string
//
//    ToString() {
//        return string.Format("<{0},{1}>", X, Y);
//    }
//
//
//    public boolean Equals(Coordinates2D other) {
//        return other.X.Equals(X) && other.Y.Equals(Y);
//    }
//
//    @Override
//    public boolean
//
//    Equals(object obj) {
//        if (ReferenceEquals(null, obj)) return false;
//        if (obj.GetType() != typeof(Coordinates2D)) return false;
//        return Equals((Coordinates2D) obj);
//    }
//
//    public override
//
//    int GetHashCode() {
//        unchecked
//        {
//            int result = X.GetHashCode();
//            result = (result * 397) ^ Y.GetHashCode();
//            return result;
//        }
//    }
//
//    public static readonly Coordinates2D
//    Zero=new
//
//    Coordinates2D(0,0);
//
//    public static readonly Coordinates2D
//    One=new
//
//    Coordinates2D(1,1);
//
//    public static readonly Coordinates2D
//    Up=new
//
//    Coordinates2D(0,1);
//
//    public static readonly Coordinates2D
//    Down=new
//
//    Coordinates2D(0,-1);
//
//    public static readonly Coordinates2D
//    Left=new
//
//    Coordinates2D(-1,0);
//
//    public static readonly Coordinates2D
//    Right=new
//
//    Coordinates2D(1,0);
//}
//
//public class SystemCoordinate {
//
//    private String Sector;
//    private int X, Y, Z;
//
//    public SystemCoordinate() {
//    }
//
//    public SystemCoordinate(String sector, int x, int y, int z) {
//        Sector = sector;
//        X = x;
//        Y = y;
//        Y = z;
//    }
//
//    public static SystemCoordinate FromStream(StarboundStream stream) {
//
//        SystemCoordinate coord = new SystemCoordinate();
//        coord.Sector = stream.readString();
//        coord.X = stream.readUnsignedInt();
//        coord.Y = stream.readUnsignedInt();
//        coord.Z = stream.readUnsignedInt();
//
//        return coord;
//
//    }
//
//    public void WriteTo(StarboundStream stream) {
//        stream.writeString(Sector);
//        stream.writeInt(X);
//        stream.writeInt(Y);
//        stream.writeInt(Z);
//    }
//
//    public String getSector() {
//        return Sector;
//    }
//
//    public void setSector(String sector) {
//        Sector = sector;
//    }
//
//    public int getX() {
//        return X;
//    }
//
//    public void setX(int x) {
//        X = x;
//    }
//
//    public int getY() {
//        return Y;
//    }
//
//    public void setY(int y) {
//        Y = y;
//    }
//
//    public int getZ() {
//        return Z;
//    }
//
//    public void setZ(int z) {
//        Z = z;
//    }
//}
//
//public class WorldCoordinate {
//
//    private String Sector;
//    private int X, Y, Z, Planet, Satellite;
//
//    public WorldCoordinate() {
//        Sector = null;
//        X = 0;
//        Y = 0;
//        Z = 0;
//        Planet = 0;
//        Satellite = 0;
//    }
//
//    public static WorldCoordinate FromStream(StarboundStream stream) {
//
//        WorldCoordinate wc = new WorldCoordinate();
//        wc.Sector = stream.readString();
//        wc.X = stream.readUnsignedInt();
//        wc.Y = stream.readUnsignedInt();
//        wc.Z = stream.readUnsignedInt();
//        wc.Planet = stream.readUnsignedInt();
//        wc.Satellite = stream.readUnsignedInt();
//        return wc;
//
//    }
//
//    public void WriteTo(StarboundStream stream) {
//        stream.writeString(Sector);
//        stream.writeInt(X);
//        stream.writeInt(Y);
//        stream.writeInt(Z);
//        stream.writeInt(Planet);
//        stream.writeInt(Satellite);
//    }
//
//    public static WorldCoordinate GetGlobalCoords(byte[] data) {
//
//        for (int i = 0; i < data.length - 26; i++) {
//            for (var sector in StarboundSector.Sectors.Select(p = > p.Data))
//            {
//
//                byte[] buffer = new byte[sector.Length];
//
//                Buffer.BlockCopy(data, i, buffer, 0, sector.Length);
//
//                if (sector.SequenceEqual(buffer)) {
//
//                    byte[] sectorData = new byte[sector.Length + 21];
//
//                    Buffer.BlockCopy(data, i - 1, sectorData, 0, sector.Length + 21);
//
//                    using(MemoryStream ms = new MemoryStream(sectorData))
//                    {
//
//                        using(StarboundStream s = new StarboundStream(ms))
//                        {
//
//                            WorldCoordinate coords = FromStream(s);
//
//                            if (string.IsNullOrEmpty(coords.Sector))
//                                return null;
//
//                            return coords;
//
//                        }
//
//                    }
//
//                }
//
//            }
//        }
//
//        return null;
//
//    }
//
//    protected bool Equals(WorldCoordinate other) {
//        return string.Equals(Sector, other.Sector) && X == other.X && Y == other.Y && Z == other.Z && Planet == other.Planet && Satellite == other.Satellite;
//    }
//
//    public override bool
//
//    Equals(object obj) {
//        if (ReferenceEquals(null, obj)) return false;
//        if (ReferenceEquals(this, obj)) return true;
//        if (obj.GetType() != this.GetType()) return false;
//        return Equals((WorldCoordinate) obj);
//    }
//
//    public override
//
//    int GetHashCode() {
//        unchecked
//        {
//            int hashCode = (Sector != null ? Sector.GetHashCode() : 0);
//            hashCode = (hashCode * 397) ^ X;
//            hashCode = (hashCode * 397) ^ Y;
//            hashCode = (hashCode * 397) ^ Z;
//            hashCode = (hashCode * 397) ^ Planet;
//            hashCode = (hashCode * 397) ^ Satellite;
//            return hashCode;
//        }
//    }
//
//    public static bool operator
//    ==(
//    WorldCoordinate w1, WorldCoordinate
//    w2)
//
//    {
//
//        if (object.ReferenceEquals(w1, w2))
//            return true;
//
//        if (object.ReferenceEquals(w1, null) || object.ReferenceEquals(w2, null))
//            return false;
//
//        return w1.Equals(w2);
//
//    }
//
//    public static bool operator
//    !=(
//    WorldCoordinate w1, WorldCoordinate
//    w2)
//
//    {
//        return !(w1 == w2);
//    }
//
//    public override string
//
//    ToString() {
//        return String.Format("[Sector: {0}, X: {1}, Y: {2}, Z: {3}, Planet {4}, Satellite: {5}]", Sector, X, Y, Z, Planet, Satellite);
//    }
//
//}
//
//public class PlanetCoordinate {
//
//    public string Sector
//
//    {
//        get;
//        set;
//    }
//
//    public ulong X
//
//    {
//        get;
//        set;
//    }
//
//    public ulong Y
//
//    {
//        get;
//        set;
//    }
//
//    public ulong Z
//
//    {
//        get;
//        set;
//    }
//
//    public ulong Planet
//
//    {
//        get;
//        set;
//    }
//
//    public ulong Satellite
//
//    {
//        get;
//        set;
//    }
//
//    public PlanetCoordinate() {
//        Sector = String.Empty;
//        X = 0;
//        Y = 0;
//        Z = 0;
//        Planet = 0;
//        Satellite = 0;
//    }
//
//}
//
//}